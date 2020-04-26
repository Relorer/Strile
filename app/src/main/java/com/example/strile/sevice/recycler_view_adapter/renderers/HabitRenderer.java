package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.HabitHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public class HabitRenderer extends BaseRenderer<CaseModel, HabitHolder> {

    private OnClickListener onClickListener;

    public HabitRenderer(OnModelChangedListener onModelChangedListener, OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public HabitHolder createViewHolder(@Nullable ViewGroup parent) {
        return new HabitHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false), onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.HABIT_TYPE;
    }
}