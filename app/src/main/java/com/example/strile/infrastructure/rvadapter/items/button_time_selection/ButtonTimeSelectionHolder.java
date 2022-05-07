package com.example.strile.infrastructure.rvadapter.items.button_time_selection;

import android.app.TimePickerDialog;
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
import java.util.Locale;

public class ButtonTimeSelectionHolder extends BaseHolder<ButtonTimeSelectionModel> {

    private final TextView text;
    private final ImageView cross;

    protected ButtonTimeSelectionHolder(@NonNull View itemView, @NonNull OnModelChangedListener<ButtonTimeSelectionModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        ImageView icon = itemView.findViewById(R.id.image_icon);
        text = itemView.findViewById(R.id.text_name);
        cross = itemView.findViewById(R.id.image_cross);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.notifications, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

        view.setOnClickListener(v -> openDialogTimeSelection());
        cross.setOnClickListener(v -> {
            model.setTime(-1);
            onModelChangedListener.onChanged(model);
            cross.setVisibility(View.INVISIBLE);
            updateText();
        });
    }

    @Override
    public void bind(ButtonTimeSelectionModel model) {
        super.bind(model);
        updateText();
        if (model.getTime() != -1) cross.setVisibility(View.VISIBLE);
        else cross.setVisibility(View.INVISIBLE);
    }

    private void updateText() {
        Calendar calendar = getModelCalendar();
        if (model.getTime() != -1) {
            final String format = view.getContext().getString(R.string.dateFormatForTime);
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            text.setText(String.format("%s %s", view.getContext().getString(R.string.at), dateFormat.format(calendar.getTime())));
        } else {
            text.setText(R.string.no_notify);
        }
    }

    private void openDialogTimeSelection() {
        Calendar calendar = getModelCalendar();
        new TimePickerDialog(view.getContext(), R.style.StandardTimePickerDialog,
                (view, hourOfDay, minute) -> {
                    model.setTime((hourOfDay * 60L + minute) * 60_000);
                    updateText();
                    cross.setVisibility(View.VISIBLE);
                    onModelChangedListener.onChanged(model);
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false)
                .show();
    }

    private Calendar getModelCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (model.getTime() != -1)
            calendar.add(Calendar.MILLISECOND, (int) model.getTime());
        else calendar.add(Calendar.HOUR_OF_DAY, 9);
        return calendar;
    }
}
