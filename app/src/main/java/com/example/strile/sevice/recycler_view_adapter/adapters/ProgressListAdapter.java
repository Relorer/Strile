package com.example.strile.sevice.recycler_view_adapter.adapters;

import androidx.recyclerview.widget.SortedList;

import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.renderers.CompleteCaseRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.GraphProgressRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.InfoProgressRenderer;

public class ProgressListAdapter extends BaseRecyclerViewAdapter {

    public ProgressListAdapter(OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new InfoProgressRenderer(onModelChangedListener));
        registerRenderer(new GraphProgressRenderer(onModelChangedListener));
        registerRenderer(new CompleteCaseRenderer(onModelChangedListener));
    }

    @Override
    protected SortedList<BaseModel> getSortedItems()  {
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
}
