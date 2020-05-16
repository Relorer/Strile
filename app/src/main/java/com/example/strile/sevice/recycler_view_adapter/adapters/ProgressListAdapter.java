package com.example.strile.sevice.recycler_view_adapter.adapters;

import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.renderers.ExecutedRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.GraphProgressRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.InfoProgressRenderer;

public class ProgressListAdapter extends BaseRecyclerViewAdapter {

    public ProgressListAdapter(OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new InfoProgressRenderer(onModelChangedListener));
        registerRenderer(new GraphProgressRenderer(onModelChangedListener));
        registerRenderer(new ExecutedRenderer(onModelChangedListener));
    }
}
