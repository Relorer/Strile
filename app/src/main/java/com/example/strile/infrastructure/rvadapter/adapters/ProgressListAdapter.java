package com.example.strile.infrastructure.rvadapter.adapters;

import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.executed.ExecutedRenderer;
import com.example.strile.infrastructure.rvadapter.items.graph_progress.GraphProgressRenderer;
import com.example.strile.infrastructure.rvadapter.items.info_progress.InfoProgressRenderer;

public class ProgressListAdapter extends BaseRecyclerViewAdapter {

    public ProgressListAdapter(OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new InfoProgressRenderer(onModelChangedListener));
        registerRenderer(new GraphProgressRenderer(onModelChangedListener));
        registerRenderer(new ExecutedRenderer(onModelChangedListener));
    }
}
