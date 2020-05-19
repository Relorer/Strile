package com.example.strile.sevice.recycler_view_adapter.items.task;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;

import java.text.SimpleDateFormat;
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
        final long deadline = model.getDeadline();
        if (new Date().getTime() >= deadline && deadline != 0 && !model.isComplete())
            info.setTextColor(view.getContext().getColor(R.color.colorRed));
        else
            info.setTextColor(view.getContext().getColor(R.color.colorLightGray));
        if (deadline == 0) info.setText(R.string.no_deadline);
        else {
            final String format = view.getContext().getString(R.string.dateFormatForDeadlineSelection);
            final SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            info.setText(dateFormat.format(deadline).toLowerCase());
        }
        binding = false;
    }
}
