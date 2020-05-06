package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.CurrentStreakHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.CurrentStreakModel;

public class CurrentStreakRenderer extends BaseRenderer<CurrentStreakModel, CurrentStreakHolder> {

    public CurrentStreakRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public CurrentStreakHolder createViewHolder(@NonNull ViewGroup parent) {
        return new CurrentStreakHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_streak, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.CURRENT_STREAK_TYPE;
    }
}
