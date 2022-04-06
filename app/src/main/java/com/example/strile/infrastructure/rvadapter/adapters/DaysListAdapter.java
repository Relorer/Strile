package com.example.strile.infrastructure.rvadapter.adapters;

import androidx.annotation.NonNull;

import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.day.DayRenderer;

public class DaysListAdapter extends BaseRecyclerViewAdapter {

    public DaysListAdapter(@NonNull OnClickListener<BaseModel> onClickItemListener,
                           @NonNull OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new DayRenderer(onModelChangedListener, onClickItemListener));
    }
}
