package com.example.strile.infrastructure.rvadapter.items.button_time_goal;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class ButtonTimeGoalRenderer extends BaseRenderer<ButtonTimeGoalModel, ButtonTimeGoalHolder> {

    public ButtonTimeGoalRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonTimeGoalHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonTimeGoalHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_icon_name, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_TIME_GOAL_TYPE;
    }
}
