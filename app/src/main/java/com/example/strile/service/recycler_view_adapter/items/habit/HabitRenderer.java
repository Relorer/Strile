package com.example.strile.service.recycler_view_adapter.items.habit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnClickListener;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.BaseRenderer;

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