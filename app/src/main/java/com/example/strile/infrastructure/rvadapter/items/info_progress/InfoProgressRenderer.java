package com.example.strile.infrastructure.rvadapter.items.info_progress;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

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
