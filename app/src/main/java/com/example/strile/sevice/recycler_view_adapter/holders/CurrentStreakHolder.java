package com.example.strile.sevice.recycler_view_adapter.holders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.CurrentStreakModel;


public class CurrentStreakHolder extends BaseHolder<CurrentStreakModel> {

    private TextView streak;

    public CurrentStreakHolder(@NonNull View itemView, OnModelChangedListener<CurrentStreakModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        streak = view.findViewById(R.id.text_streak);
    }

    @Override
    protected void _bind() {
        super._bind();
        streak.setText(model.getStreak() + " days");
    }
}
