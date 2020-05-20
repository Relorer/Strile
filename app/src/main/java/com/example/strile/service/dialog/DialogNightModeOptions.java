package com.example.strile.service.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import com.example.strile.R;
import com.example.strile.service.call_back_interfaces.CompleteCallback;

import java.util.Objects;

public class DialogNightModeOptions extends DialogFragment {

    private CompleteCallback<Integer> callback;
    private int nightMode;
    private int selected;

    public DialogNightModeOptions(@AppCompatDelegate.NightMode int mode, @NonNull CompleteCallback<Integer> callback) {
        this.callback = callback;
        nightMode = mode;
    }

    public DialogNightModeOptions() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (callback == null && getDialog() != null)
            getDialog().cancel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final String[] options = {
                getString(R.string.follow_system),
                getString(R.string.disabled),
                getString(R.string.enabled)
        };

        switch (nightMode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                selected = 1;
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                selected = 2;
                break;
            default:
                selected = 0;
                break;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity(),
                "When creating the dialog box getActivity returned null"), R.style.StandardDialog);
        builder.setSingleChoiceItems(options, selected,
                (dialog, item) -> selected = item)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    int nightMode;
                    if (selected == 0) nightMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
                    else if (selected == 1) nightMode = AppCompatDelegate.MODE_NIGHT_NO;
                    else nightMode = AppCompatDelegate.MODE_NIGHT_YES;
                    callback.onComplete(nightMode);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {

                });

        return builder.create();
    }
}
