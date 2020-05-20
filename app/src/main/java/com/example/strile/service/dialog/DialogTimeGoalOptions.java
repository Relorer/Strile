package com.example.strile.service.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.strile.R;
import com.example.strile.service.call_back_interfaces.CompleteCallback;

import java.util.Objects;

public class DialogTimeGoalOptions extends DialogFragment {

    private CompleteCallback<Long> callback;
    private int selected = 0;

    public DialogTimeGoalOptions(long time, @NonNull CompleteCallback<Long> callback) {
        this.callback = callback;

        final long numMinutes = time / 1000 / 60;
        if (numMinutes == 5) selected = 1;
        else if (numMinutes == 15) selected = 2;
        else if (numMinutes == 30) selected = 3;
        else if (numMinutes == 45) selected = 4;
        else if (numMinutes == 60) selected = 5;
    }

    public DialogTimeGoalOptions() {

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
        final String[] timeOptions = {
                getString(R.string.no_time),
                String.format("5 %s", getResources().getQuantityString(R.plurals.minutes_without_number, 5)),
                String.format("15 %s", getResources().getQuantityString(R.plurals.minutes_without_number, 15)),
                String.format("30 %s", getResources().getQuantityString(R.plurals.minutes_without_number, 30)),
                String.format("45 %s", getResources().getQuantityString(R.plurals.minutes_without_number, 45)),
                getString(R.string.hour)
        };

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity(),
                "When creating the dialog box getActivity returned null"), R.style.StandardDialog);
        builder.setSingleChoiceItems(timeOptions, selected,
                (dialog, item) -> selected = item)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    long time = 60 * 1000;
                    if (selected == 0) time *= 0;
                    else if (selected == 1) time *= 5;
                    else if (selected == 2) time *= 15;
                    else if (selected == 3) time *= 30;
                    else if (selected == 4) time *= 45;
                    else if (selected == 5) time *= 60;
                    callback.onComplete(time);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {

                });

        return builder.create();
    }
}
