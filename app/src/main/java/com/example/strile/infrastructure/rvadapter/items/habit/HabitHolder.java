package com.example.strile.infrastructure.rvadapter.items.habit;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;

import java.util.Locale;

public class HabitHolder extends BaseHolder<HabitModel> {

    private final CheckBox done;
    private final TextView name;
    private final TextView info;
    private final ImageView special;

    private boolean binding = false;

    private final MediaPlayer checked;

    public HabitHolder(@NonNull View itemView,
                       @NonNull final OnModelChangedListener<HabitModel> onModelChangedListener,
                       @NonNull final OnClickListener<HabitModel> onClickCaseListener) {
        super(itemView, onModelChangedListener);

        done = itemView.findViewById(R.id.image_icon_settings);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        special = itemView.findViewById(R.id.image_special);

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
    public void bind(HabitModel model) {
        binding = true;
        super.bind(model);
        name.setText(model.getName());
        done.setChecked(model.isComplete());
        int streak = model.getStreak();
        info.setText(view.getContext().getResources().getQuantityString(R.plurals.day_streak, streak, streak, Locale.getDefault()));
        if (model.isGoalTime()) {
            special.setImageDrawable(ResourcesCompat.getDrawable(view.getResources(), R.drawable.time_goal, null));
        } else {
            special.setImageDrawable(null);
        }

        binding = false;
    }
}