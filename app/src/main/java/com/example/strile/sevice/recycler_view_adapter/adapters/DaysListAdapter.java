package com.example.strile.sevice.recycler_view_adapter.adapters;

import android.util.Log;

import androidx.recyclerview.widget.SortedList;

import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.renderers.DayRenderer;

public class DaysListAdapter extends BaseRecyclerViewAdapter {

    private OnClickListener<BaseModel> onClickItemListener;
    private OnModelChangedListener<BaseModel> onModelChangedListener;

    @Override
    protected void updateRenderers() {
        super.updateRenderers();
        registerRenderer(new DayRenderer(onModelChangedListener, onClickItemListener));
    }

    public void setOnClickItemListener(OnClickListener<BaseModel> onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
        updateRenderers();
    }
}
