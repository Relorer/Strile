package com.example.strile.sevice.recycler_view_adapter;

import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.List;

public abstract class RendererRecyclerViewAdapter extends RecyclerView.Adapter {

    @NonNull
    private SortedList<ItemModel> sortedItems;

    @NonNull
    private final SparseArray<ViewRenderer> renderers = new SparseArray<>();

    protected RendererRecyclerViewAdapter() {
        sortedItems = getSortedItems();
    }

    protected abstract SortedList<ItemModel> getSortedItems();

    @Override
    public long getItemId(int position) {
        return sortedItems.get(position).getId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ViewRenderer renderer = renderers.get(viewType);
        if (renderer != null) {
            return renderer.createViewHolder(parent);
        }

        throw new RuntimeException("Not supported Item View Type: " + viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ItemModel item = getItem(position);

        final ViewRenderer renderer = renderers.get(item.getType());
        if (renderer != null) {
            renderer.bindView(item, holder);
        } else {
            throw new RuntimeException("Not supported View Holder: " + holder);
        }
    }

    @Override
    public int getItemCount() {
        return sortedItems.size();
    }

    @Override
    public int getItemViewType(final int position) {
        final ItemModel item = getItem(position);
        return item.getType();
    }

    protected void registerRenderer(@NonNull final ViewRenderer renderer) {
        final int type = renderer.getType();

        if (renderers.get(type) == null) {
            renderers.put(type, renderer);
        } else {
            throw new RuntimeException("ViewRenderer already exist with this type: " + type);
        }
    }

    public void setItems(@NonNull List<ItemModel> items) {
        sortedItems.replaceAll(items);
    }

    private ItemModel getItem(final int position) {
        return sortedItems.get(position);
    }
}
