package com.example.strile.sevice.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.strile.sevice.call_back_interfaces.CompleteDialogRepeatCallback;

public class DialogRepeatOptions extends DialogFragment {

    CompleteDialogRepeatCallback callback;

    boolean[] checkedDays;

    public DialogRepeatOptions(boolean[] checkedDays, CompleteDialogRepeatCallback callback) {
        this.callback = callback;
        this.checkedDays = checkedDays;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final String[] repeatOptions = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMultiChoiceItems(repeatOptions, checkedDays,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedDays[which] = isChecked;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (callback != null) {
                            callback.onComplete(checkedDays);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


        return builder.create();
    }
}
