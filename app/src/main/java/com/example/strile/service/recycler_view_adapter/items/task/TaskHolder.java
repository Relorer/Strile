package com.example.strile.service.recycler_view_adapter.items.task;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.date.Day;
import com.example.strile.service.event_handler_interfaces.OnClickListener;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskHolder extends BaseHolder<TaskModel> {

    private final CheckBox done;
    private final TextView name;
    private final TextView info;

    private boolean binding = false;

    public TaskHolder(@NonNull View itemView,
                      @NonNull final OnModelChangedListener<TaskModel> onModelChangedListener,
                      @NonNull final OnClickListener<TaskModel> onClickCaseListener) {
        super(itemView, onModelChangedListener);

        done = itemView.findViewById(R.id.image_icon_settings);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!binding)
                onModelChangedListener.onChanged(model.setState(isChecked));
        });
        itemView.setOnClickListener(v -> onClickCaseListener.onClick(model));
    }

    @Override
    public void bind(TaskModel model) {
        binding = true;
        super.bind(model);
        name.setText(model.getName());
        done.setChecked(model.isComplete());

        Calendar yesterday = Calendar.getInstance();
        yesterday.setTime(Day.getDateOfDayWithoutTime(new Date()));
        yesterday.add(Calendar.DATE, -1);

        Calendar today = Calendar.getInstance();
        today.setTime(Day.getDateOfDayWithoutTime(new Date()));

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(Day.getDateOfDayWithoutTime(new Date()));
        tomorrow.add(Calendar.DATE, 1);

        final long deadline = model.getDeadline();

        if (deadline <= yesterday.getTimeInMillis() && deadline != 0 && !model.isComplete())
            info.setTextColor(view.getContext().getColor(R.color.colorRed));
        else if ((deadline == today.getTimeInMillis() || deadline == tomorrow.getTimeInMillis()) && !model.isComplete()) {
            info.setTextColor(view.getContext().getColor(R.color.colorAccent));
        } else {
            info.setTextColor(view.getContext().getColor(R.color.colorLightGray));
        }

        if (deadline == 0) info.setText(R.string.no_deadline);
        else if (deadline == yesterday.getTimeInMillis() && !model.isComplete()) {
            info.setText(R.string.yesterday);
        } else if (deadline == today.getTimeInMillis() && !model.isComplete()) {
            info.setText(R.string.today);
        } else if (deadline == tomorrow.getTimeInMillis() && !model.isComplete()) {
            info.setText(R.string.tomorrow);
        } else {
            final String format = view.getContext().getString(R.string.dateFormatForDeadlineSelection);
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            info.setText(dateFormat.format(deadline));
        }
        binding = false;
    }
}
