package com.example.strile.sevice.recycler_view_adapter.items;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

public abstract class BaseRenderer<M extends BaseModel, VH extends BaseHolder<M>> {

    protected final OnModelChangedListener onModelChangedListener;

    public BaseRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        this.onModelChangedListener = onModelChangedListener;
    }

    public void bindView(@NonNull M model, @NonNull VH holder) {
        holder.bind(model);
    }

    @NonNull
    public abstract VH createViewHolder(@NonNull ViewGroup parent);

    public abstract int getType();
}