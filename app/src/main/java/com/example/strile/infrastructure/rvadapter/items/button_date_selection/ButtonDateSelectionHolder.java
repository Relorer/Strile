package com.example.strile.infrastructure.rvadapter.items.button_date_selection;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;

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
        ImageView icon = itemView.findViewById(R.id.image_icon);
        text = itemView.findViewById(R.id.text_name);
        buttonDelete = itemView.findViewById(R.id.image_cross);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.calendar, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

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
