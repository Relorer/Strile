package com.example.strile.sevice.recycler_view_adapter.items.seek_bar_difficult;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.BaseRenderer;

public class SeekBarDifficultRenderer extends BaseRenderer<SeekBarDifficultModel, SeekBarDifficultHolder> {

    public SeekBarDifficultRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public SeekBarDifficultHolder createViewHolder(@NonNull ViewGroup parent) {
        return new SeekBarDifficultHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seek_bar_difficult, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.SEEK_BAR_DIFFICULT_TYPE;
    }
}
