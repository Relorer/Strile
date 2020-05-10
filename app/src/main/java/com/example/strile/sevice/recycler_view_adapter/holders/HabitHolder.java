package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.HabitModel;

public class HabitHolder extends BaseHolder<HabitModel> {

    private final CheckBox done;
    private final TextView name;
    private final TextView info;
    private final ImageView special;

    private boolean binding = false;

    public HabitHolder(@NonNull View itemView,
                       @NonNull final OnModelChangedListener<HabitModel> onModelChangedListener,
                       @NonNull final OnClickListener<HabitModel> onClickCaseListener) {
        super(itemView, onModelChangedListener);

        done = itemView.findViewById(R.id.checkbox_done);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        special = itemView.findViewById(R.id.image_special);
        done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!binding)
                onModelChangedListener.onChanged(model.setState(isChecked));
        });
        itemView.setOnClickListener(v -> {
            onClickCaseListener.onClick(model);
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(HabitModel model) {
        binding = true;
        super.bind(model);
        name.setText(model.getName());
        done.setChecked(model.isComplete());
        info.setText(String.format("%d %s", model.getStreak(), view.getContext().getString(R.string.day_streak)));
        if (model.isGoalTime()) special.setImageDrawable(null);
        else
            special.setImageDrawable(ResourcesCompat.getDrawable(view.getResources(), R.drawable.time_goal, null));
        binding = false;
    }
}