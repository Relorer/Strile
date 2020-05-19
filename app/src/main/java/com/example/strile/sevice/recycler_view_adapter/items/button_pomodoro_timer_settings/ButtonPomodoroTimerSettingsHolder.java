package com.example.strile.sevice.recycler_view_adapter.items.button_pomodoro_timer_settings;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ButtonPomodoroTimerSettingsHolder extends BaseHolder<ButtonPomodoroTimerSettingsModel> {

    private final ImageView icon;
    private final TextView name;
    private final TextView info;

    public ButtonPomodoroTimerSettingsHolder(@NonNull View itemView,
                                             @NonNull OnModelChangedListener<ButtonPomodoroTimerSettingsModel> onModelChangedListener,
                                             @NonNull final OnClickListener<BaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        icon = itemView.findViewById(R.id.image_icon_settings);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.timer, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

        name.setText(R.string.focus_timer);

        itemView.setOnClickListener(view -> onClickListener.onClick(model));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(ButtonPomodoroTimerSettingsModel model) {
        super.bind(model);
        info.setText(String.format("%s: %d %d %d %d",
                view.getContext().getString(R.string.scheme),
                model.getPomodoroTimeGoal() / 60_000,
                model.getShortBreakTimeGoal() / 60_000,
                model.getLongBreakTimeGoal() / 60_000,
                model.getFrequencyLongBreak()));
    }
}
