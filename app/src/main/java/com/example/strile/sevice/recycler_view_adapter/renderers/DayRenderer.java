package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.DayHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.DayModel;

public class DayRenderer extends BaseRenderer<DayModel, DayHolder> {

    private OnClickListener onClickListener;

    public DayRenderer(OnModelChangedListener onModelChangedListener, OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public DayHolder createViewHolder(@Nullable ViewGroup parent) {
        return new DayHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.DAY_TYPE;
    }
}
