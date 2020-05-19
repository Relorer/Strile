package com.example.strile.sevice.recycler_view_adapter.items.edit_text;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.BaseRenderer;

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
