package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.ButtonTimeGoalHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;

public class ButtonTimeGoalRenderer extends BaseRenderer<ButtonTimeGoalModel, ButtonTimeGoalHolder> {


    public ButtonTimeGoalRenderer(OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public ButtonTimeGoalHolder createViewHolder(@Nullable ViewGroup parent) {
        return new ButtonTimeGoalHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.button_time_goal, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_TIME_GOAL_TYPE;
    }
}
