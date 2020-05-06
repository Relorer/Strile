package com.example.strile.sevice.recycler_view_adapter.adapters;

import android.util.SparseArray;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.strile.sevice.recycler_view_adapter.holders.BaseHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.renderers.BaseRenderer;

import java.util.List;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter {

    @NonNull
    private final SortedList<BaseModel> sortedItems;

    @NonNull
    private final SparseArray<BaseRenderer> renderers = new SparseArray<>();

    protected BaseRecyclerViewAdapter() {
        sortedItems = getSortedItems();
    }

    protected SortedList<BaseModel> getSortedItems() {
        return new SortedList<>(BaseModel.class, new SortedList.Callback<BaseModel>() {
            @Override
            public int compare(BaseModel o1, BaseModel o2) {
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(BaseModel oldItem, BaseModel newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(BaseModel item1, BaseModel item2) {
                return item1.getId() == item2.getId();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return sortedItems.get(position).getId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final BaseRenderer renderer = renderers.get(viewType);
        if (renderer != null) {
            return renderer.createViewHolder(parent);
        }

        throw new RuntimeException("Not supported Item View Type: " + viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final BaseModel item = getItem(position);

        final BaseRenderer renderer = renderers.get(item.getType());
        if (renderer != null) {
            renderer.bindView(item, (BaseHolder) holder);
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
        final BaseModel item = getItem(position);
        return item.getType();
    }

    protected void registerRenderer(@NonNull final BaseRenderer renderer) {
        final int type = renderer.getType();

        if (renderers.get(type) == null) {
            renderers.put(type, renderer);
        } else {
            throw new RuntimeException("ViewRenderer already exist with this type: " + type);
        }
    }

    public void setItems(@NonNull List<BaseModel> items) {
        sortedItems.replaceAll(items);
    }

    private BaseModel getItem(final int position) {
        return sortedItems.get(position);
    }
}