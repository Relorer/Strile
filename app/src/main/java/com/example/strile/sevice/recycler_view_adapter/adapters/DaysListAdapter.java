package com.example.strile.sevice.recycler_view_adapter.adapters;

import androidx.annotation.NonNull;

import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.day.DayRenderer;

public class DaysListAdapter extends BaseRecyclerViewAdapter {

    public DaysListAdapter(@NonNull OnClickListener<BaseModel> onClickItemListener,
                           @NonNull OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new DayRenderer(onModelChangedListener, onClickItemListener));
    }
}
