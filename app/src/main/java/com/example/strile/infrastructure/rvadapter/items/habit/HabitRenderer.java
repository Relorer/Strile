package com.example.strile.infrastructure.rvadapter.items.habit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

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