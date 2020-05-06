package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.InfoProgressHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.InfoProgressModel;

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
