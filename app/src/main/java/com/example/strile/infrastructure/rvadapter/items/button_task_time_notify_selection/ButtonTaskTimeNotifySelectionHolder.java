package com.example.strile.infrastructure.rvadapter.items.button_task_time_notify_selection;

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

public class ButtonTaskTimeNotifySelectionHolder extends BaseHolder<ButtonTaskTimeNotifySelectionModel> {

    private final TextView info;
    private final ImageView cross;

    protected ButtonTaskTimeNotifySelectionHolder(@NonNull View itemView, @NonNull OnModelChangedListener onModelChangedListener) {
        super(itemView, onModelChangedListener);
        ImageView icon = itemView.findViewById(R.id.image_icon_settings);
        TextView name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        cross = itemView.findViewById(R.id.image_cross);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.event_note, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

        name.setText(R.string.notify_upcoming_task);

        itemView.setOnClickListener(view -> openDialogTimeSelection());
        cross.setOnClickListener(view -> {
            model.setTime(-1);
            updateTextInfo();
            cross.setVisibility(View.INVISIBLE);
            onModelChangedListener.onChanged(model);
        });
    }

    @Override
    public void bind(ButtonTaskTimeNotifySelectionModel model) {
        super.bind(model);
        updateTextInfo();
        if (model.getTime() != -1) cross.setVisibility(View.VISIBLE);
        else cross.setVisibility(View.INVISIBLE);
    }

    private void updateTextInfo() {
        Calendar calendar = getModelCalendar();
        if (model.getTime() != -1) {
            final String format = view.getContext().getString(R.string.dateFormatForTime);
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            info.setText(String.format("%s %s", view.getContext().getString(R.string.at), dateFormat.format(calendar.getTime())));
        } else {
            info.setText(R.string.no_notify);
        }
    }

    private void openDialogTimeSelection() {
        Calendar calendar = getModelCalendar();
        new TimePickerDialog(view.getContext(), R.style.StandardTimePickerDialog,
                (view, hourOfDay, minute) -> {
                    model.setTime((hourOfDay * 60L + minute) * 60_000);
                    updateTextInfo();
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
