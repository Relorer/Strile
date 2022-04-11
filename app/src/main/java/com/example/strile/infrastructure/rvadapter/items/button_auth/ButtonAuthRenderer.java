package com.example.strile.infrastructure.rvadapter.items.button_auth;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;

public class ButtonAuthRenderer extends BaseRenderer<ButtonAuthModel, ButtonAuthHolder> {

    private final OnClickListener onClickListener;

    public ButtonAuthRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                                    @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ButtonAuthHolder createViewHolder(@NonNull ViewGroup parent) {
        return new ButtonAuthHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.button_auth, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.BUTTON_AUTH;
    }
}
