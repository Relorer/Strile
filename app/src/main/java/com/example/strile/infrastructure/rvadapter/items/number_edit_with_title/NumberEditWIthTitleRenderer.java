package com.example.strile.infrastructure.rvadapter.items.number_edit_with_title;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class NumberEditWIthTitleRenderer extends BaseRenderer<NumberEditWIthTitleModel, NumberEditWIthTitleHolder> {


    public NumberEditWIthTitleRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public NumberEditWIthTitleHolder createViewHolder(@NonNull ViewGroup parent) {
        return new NumberEditWIthTitleHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seek_bar_number, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.EDIT_NUMBER_TYPE;
    }
}
