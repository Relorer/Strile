package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.GraphProgressHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.GraphProgressModel;

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
