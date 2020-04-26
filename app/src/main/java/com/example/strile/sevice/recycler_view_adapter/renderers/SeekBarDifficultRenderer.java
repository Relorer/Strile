package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.event_handler_interfaces.OnSeekBarChangeListener;
import com.example.strile.sevice.recycler_view_adapter.holders.SeekBarDifficultHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

public class SeekBarDifficultRenderer extends BaseRenderer<SeekBarDifficultModel, SeekBarDifficultHolder> {


    public SeekBarDifficultRenderer(OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public SeekBarDifficultHolder createViewHolder(@Nullable ViewGroup parent) {
        return new SeekBarDifficultHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.seek_bar_difficult, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.SEEK_BAR_DIFFICULT_TYPE;
    }
}
