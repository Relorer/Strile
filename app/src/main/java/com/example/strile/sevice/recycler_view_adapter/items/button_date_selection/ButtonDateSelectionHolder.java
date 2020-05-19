package com.example.strile.sevice.recycler_view_adapter.items.button_date_selection;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ButtonDateSelectionHolder extends BaseHolder<ButtonDateSelectionModel> {

    private final TextView text;
    private final View buttonDelete;

    public ButtonDateSelectionHolder(@NonNull View itemView,
                                     @NonNull final OnModelChangedListener<ButtonDateSelectionModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        text = itemView.findViewById(R.id.text_deadline);
        buttonDelete = itemView.findViewById(R.id.image_cross);

        view.setOnClickListener(v -> openDialogSetDate(model.getDate()));
        buttonDelete.setOnClickListener(v -> {
            onModelChangedListener.onChanged(model.setDate(new Date(0)));
            changeTextDeadline();
            buttonDelete.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void bind(ButtonDateSelectionModel model) {
        super.bind(model);
        changeTextDeadline();
        if (model.getDate().getTime() == 0)
            buttonDelete.setVisibility(View.INVISIBLE);
        else
            buttonDelete.setVisibility(View.VISIBLE);
    }

    private void changeTextDeadline() {
        if (model.getDate().getTime() != 0) {
            final String format = view.getContext().getString(R.string.dateFormatForDeadlineSelection);
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            text.setText(String.format("%s %s", view.getContext().getString(R.string.by), dateFormat.format(model.getDate())));

        } else {
            text.setText(R.string.add_deadline);
        }
    }

    private void openDialogSetDate(Date date) {
        final Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTime(date);

        final DatePickerDialog dialog = new DatePickerDialog(view.getContext(), R.style.StandardDatePickerDialog, (view, year, month, dayOfMonth) -> {
            dateAndTime.set(year, month, dayOfMonth, 0, 0, 0);
            onModelChangedListener.onChanged(model.setDate(dateAndTime.getTime()));
            changeTextDeadline();
            buttonDelete.setVisibility(View.VISIBLE);
        },
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate((new Date().getTime()));
        dialog.show();
    }
}
