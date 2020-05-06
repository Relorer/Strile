package com.example.strile.sevice.recycler_view_adapter.holders;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            onModelChangedListener.onChanged(new ButtonDateSelectionModel(model.isTopMargin(), new Date(0)));
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
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(model.getDate());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");
//todo data format no checked
            text.setText(String.format("%s %s",
                    view.getContext().getString(R.string.deadline_by),
                    simpleDateFormat.format(model.getDate())));

//            text.setText(String.format("%s%s %d, %d", view.getContext().getString(R.string.deadline_by),
//                    calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    calendar.get(Calendar.YEAR)));

        } else {
            text.setText(R.string.add_deadline);
        }
    }

    private void openDialogSetDate(Date date) {
        final Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTime(date);

        final DatePickerDialog dialog = new DatePickerDialog(view.getContext(), (view, year, month, dayOfMonth) -> {
            dateAndTime.set(year, month, dayOfMonth, 0, 0, 0);
            onModelChangedListener.onChanged(new ButtonDateSelectionModel(model.isTopMargin(), dateAndTime.getTime()));
        },
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate((new Date().getTime()));
        dialog.show();
    }
}
