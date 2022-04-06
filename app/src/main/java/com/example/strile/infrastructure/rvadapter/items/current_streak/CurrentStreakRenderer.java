package com.example.strile.infrastructure.rvadapter.items.current_streak;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class CurrentStreakRenderer extends BaseRenderer<CurrentStreakModel, CurrentStreakHolder> {

    public CurrentStreakRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public CurrentStreakHolder createViewHolder(@NonNull ViewGroup parent) {
        return new CurrentStreakHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_streak, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.CURRENT_STREAK_TYPE;
    }
}
