package com.example.strile.sevice.recycler_view_adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public
abstract
class ViewRenderer<M extends ItemModel, VH extends RecyclerView.ViewHolder> {
    public abstract void bindView(@NonNull M model, @NonNull VH holder);

    @NonNull
    public abstract VH createViewHolder(@Nullable ViewGroup parent);

    public abstract int getType();
}