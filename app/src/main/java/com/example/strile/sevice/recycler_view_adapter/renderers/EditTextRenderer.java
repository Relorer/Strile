package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.event_handler_interfaces.OnTextChangeListener;
import com.example.strile.sevice.recycler_view_adapter.holders.EditTextHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;

public class EditTextRenderer extends BaseRenderer<EditTextModel, EditTextHolder> {


    public EditTextRenderer(OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public EditTextHolder createViewHolder(@Nullable ViewGroup parent) {
        return new EditTextHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edit_text, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.EDIT_TEXT_TYPE;
    }
}
