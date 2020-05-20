package com.example.strile.service.recycler_view_adapter.items.graph_progress;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.BaseRenderer;

public class GraphProgressRenderer extends BaseRenderer<GraphProgressModel, GraphProgressHolder> {

    public GraphProgressRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public GraphProgressHolder createViewHolder(@NonNull ViewGroup parent) {
        return new GraphProgressHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.graph_progress, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.GRAPH_PROGRESS_TYPE;
    }
}
