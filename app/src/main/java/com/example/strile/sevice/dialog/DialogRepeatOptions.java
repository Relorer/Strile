package com.example.strile.sevice.dialog;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.strile.R;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class DialogRepeatOptions extends DialogFragment {

    private final CompleteCallback<boolean[]> callback;
    private final boolean[] checkedDays;

    public DialogRepeatOptions(@NonNull boolean[] checkedDays, @NonNull CompleteCallback<boolean[]> callback) {
        this.callback = callback;
        this.checkedDays = checkedDays;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        final String[] repeatOptions = symbols.getWeekdays();

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity(),
                "When creating the dialog box getActivity returned null"));
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
