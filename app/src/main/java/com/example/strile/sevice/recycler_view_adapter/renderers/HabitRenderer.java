package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.HabitHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.HabitModel;

public class HabitRenderer extends BaseRenderer<HabitModel, HabitHolder> {

    private final OnClickListener onClickListener;

    public HabitRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                         @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public HabitHolder createViewHolder(@NonNull ViewGroup parent) {
        return new HabitHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_journal, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.HABIT_TYPE;
    }
}