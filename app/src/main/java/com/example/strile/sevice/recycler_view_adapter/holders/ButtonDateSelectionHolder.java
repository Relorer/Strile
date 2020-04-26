package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ButtonDateSelectionHolder extends BaseHolder<ButtonDateSelectionModel> {

    private View view;
    private TextView text;
    private View buttonDelete;

    public ButtonDateSelectionHolder(@NonNull View itemView,
                                     final OnModelChangedListener<ButtonDateSelectionModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        view = itemView;
        text = itemView.findViewById(R.id.text_deadline);
        buttonDelete = itemView.findViewById(R.id.image_cross);

        view.setOnClickListener(v -> openDialogSetDate(model.getDate()));
        buttonDelete.setOnClickListener(v -> {
            model.setDate(0);
            changeTextDeadline();
            buttonDelete.setVisibility(View.INVISIBLE);
            if (onModelChangedListener != null)
                onModelChangedListener.onChanged(model);
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        changeTextDeadline();
        if (model.getDate() == 0)
            buttonDelete.setVisibility(View.INVISIBLE);
        else
            buttonDelete.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void changeTextDeadline() {
        if (model.getDate() != 0) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(model.getDate());
            text.setText("By " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR));
        } else {
            text.setText("Add deadline");
        }
    }

    private void openDialogSetDate(long milliseconds) {
        final Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(milliseconds);
        DatePickerDialog dialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateAndTime.set(year, month, dayOfMonth, 0, 0, 0);
                model.setDate(dateAndTime.getTime().getTime());
                changeTextDeadline();
                buttonDelete.setVisibility(View.VISIBLE);
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }
        },
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate((new Date().getTime()));
        dialog.show();
    }
}
