package com.example.strile.infrastructure.rvadapter.items.edit_text;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class EditTextRenderer extends BaseRenderer<EditTextModel, EditTextHolder> {

    public EditTextRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public EditTextHolder createViewHolder(@NonNull ViewGroup parent) {
        return new EditTextHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edit_text, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.EDIT_TEXT_TYPE;
    }
}
