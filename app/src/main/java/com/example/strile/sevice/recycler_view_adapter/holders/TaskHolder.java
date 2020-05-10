package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.TaskModel;

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

        done = itemView.findViewById(R.id.checkbox_done);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!binding)
                onModelChangedListener.onChanged(model.setState(isChecked));
        });
        itemView.setOnClickListener(v -> {
            onClickCaseListener.onClick(model);
        });
    }

    @Override
    public void bind(TaskModel model) {
        binding = true;
        super.bind(model);
        name.setText(model.getName());
        done.setChecked(model.isComplete());
        final long deadline = model.getDeadline();
        if (new Date().getTime() >= deadline && deadline != 0)
            info.setTextColor(view.getContext().getColor(R.color.colorRed));
        else
            info.setTextColor(view.getContext().getColor(R.color.colorLightGray));
        if (deadline == 0) info.setText(R.string.no_deadline);
        else {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(deadline);
            info.setText(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR));
        }
        binding = false;
    }
}
