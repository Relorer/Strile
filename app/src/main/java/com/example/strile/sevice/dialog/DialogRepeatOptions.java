package com.example.strile.sevice.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.strile.R;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class DialogRepeatOptions extends DialogFragment {

    private CompleteCallback<boolean[]> callback;
    private boolean[] checkedDays;

    public DialogRepeatOptions(@NonNull boolean[] checkedDays, @NonNull CompleteCallback<boolean[]> callback) {
        this.callback = callback;
        this.checkedDays = checkedDays;
    }

    public DialogRepeatOptions() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (checkedDays == null)
            getDialog().cancel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        final String[] repeatOptions = new String[7];
        System.arraycopy(symbols.getWeekdays(), 1, repeatOptions, 0, 7);

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity(),
                "When creating the dialog box getActivity returned null"), R.style.StandardDialog);
        builder.setMultiChoiceItems(repeatOptions, checkedDays,
                (dialog, which, isChecked) -> checkedDays[which] = isChecked)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    callback.onComplete(checkedDays);
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {

                });

        return builder.create();
    }
}