package com.example.strile.infrastructure.rvadapter.items.graph_progress;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

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
