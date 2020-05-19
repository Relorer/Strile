package com.example.strile.sevice.recycler_view_adapter.items.info_progress;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.BaseRenderer;

public class InfoProgressRenderer extends BaseRenderer<InfoProgressModel, InfoProgressHolder> {

    public InfoProgressRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public InfoProgressHolder createViewHolder(@NonNull ViewGroup parent) {
        return new InfoProgressHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_progress, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.INFO_PROGRESS_TYPE;
    }
}
