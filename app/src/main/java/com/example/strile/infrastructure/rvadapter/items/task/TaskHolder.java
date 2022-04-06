package com.example.strile.infrastructure.rvadapter.items.task;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.extensions.DateUtilities;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskHolder extends BaseHolder<TaskModel> {

    private final CheckBox done;
    private final TextView name;
    private final TextView info;

    private boolean binding = false;

    private final MediaPlayer checked;

    public TaskHolder(@NonNull View itemView,
                      @NonNull final OnModelChangedListener<TaskModel> onModelChangedListener,
                      @NonNull final OnClickListener<TaskModel> onClickCaseListener) {
        super(itemView, onModelChangedListener);

        done = itemView.findViewById(R.id.image_icon_settings);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);

        checked = MediaPlayer.create(itemView.getContext(), R.raw.hero_simple_celebration);

        done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!binding) {
                onModelChangedListener.onChanged(model.setState(isChecked));
                if (isChecked) checked.start();
            }
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
        yesterday.setTime(DateUtilities.getDateOfDayWithoutTime(new Date()));
        yesterday.add(Calendar.DATE, -1);

        Calendar today = Calendar.getInstance();
        today.setTime(DateUtilities.getDateOfDayWithoutTime(new Date()));

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(DateUtilities.getDateOfDayWithoutTime(new Date()));
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
