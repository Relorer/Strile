package com.example.strile.infrastructure.rvadapter.items.day;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class DayRenderer extends BaseRenderer<DayModel, DayHolder> {

    private final OnClickListener onClickListener;

    public DayRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                       @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public DayHolder createViewHolder(@NonNull ViewGroup parent) {
        return new DayHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.DAY_TYPE;
    }
}
