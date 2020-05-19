package com.example.strile.sevice.recycler_view_adapter.adapters;

import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.executed.ExecutedRenderer;
import com.example.strile.sevice.recycler_view_adapter.items.graph_progress.GraphProgressRenderer;
import com.example.strile.sevice.recycler_view_adapter.items.info_progress.InfoProgressRenderer;

public class ProgressListAdapter extends BaseRecyclerViewAdapter {

    public ProgressListAdapter(OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new InfoProgressRenderer(onModelChangedListener));
        registerRenderer(new GraphProgressRenderer(onModelChangedListener));
        registerRenderer(new ExecutedRenderer(onModelChangedListener));
    }
}
