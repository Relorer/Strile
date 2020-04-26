package com.example.strile.sevice.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.strile.sevice.call_back_interfaces.CompleteDialogTimeGoalCallback;

public class DialogTimeGoalOptions extends DialogFragment {

    CompleteDialogTimeGoalCallback callback;
    private int selected = -1;

    public DialogTimeGoalOptions(int timeSeconds, CompleteDialogTimeGoalCallback callback) {
        this.callback = callback;
        switch (timeSeconds) {
            case 0:
                selected = 0;
                break;
            case 5 * 60:
                selected = 1;
                break;
            case 15 * 60:
                selected = 2;
                break;
            case 30 * 60:
                selected = 3;
                break;
            case 60 * 60:
                selected = 4;
                break;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final String[] timeOptions = {"no time", "5 minutes", "15 minutes", "30 minutes", "1 hour"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setSingleChoiceItems(timeOptions, selected,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        selected = item;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (callback != null) {
                            switch (selected) {
                                case 0:
                                    callback.onComplete(0);
                                    break;
                                case 1:
                                    callback.onComplete(5 * 60);
                                    break;
                                case 2:
                                    callback.onComplete(15 * 60);
                                    break;
                                case 3:
                                    callback.onComplete(30 * 60);
                                    break;
                                case 4:
                                    callback.onComplete(60 * 60);
                                    break;
                            }
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
