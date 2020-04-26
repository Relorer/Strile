package com.example.strile.sevice.recycler_view_adapter.adapters;

import android.util.Log;

import androidx.recyclerview.widget.SortedList;

import com.example.strile.database.entities.CaseModel;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.renderers.ButtonHidingRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.HabitRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.TaskRenderer;
import com.example.strile.sevice.structures.DateCompleted;

public class JournalListAdapter extends BaseRecyclerViewAdapter {

    private OnClickListener<BaseModel> onClickItemListener;
    private OnModelChangedListener<BaseModel> onModelChangedListener;

    protected void updateRenderers() {
        super.updateRenderers();
        registerRenderer(new HabitRenderer(onModelChangedListener, onClickItemListener));
        registerRenderer(new TaskRenderer(onModelChangedListener, onClickItemListener));
        registerRenderer(new ButtonHidingRenderer(onModelChangedListener));
    }

    @Override
    protected SortedList<BaseModel> getSortedItems() {
        return new SortedList<>(BaseModel.class, new SortedList.Callback<BaseModel>() {
            @Override
            public int compare(BaseModel o1, BaseModel o2) {
                if (o1 instanceof ButtonHidingModel) return 1;
                if (o2 instanceof ButtonHidingModel) return -1;
                CaseModel c1 = (CaseModel) o1;
                CaseModel c2 = (CaseModel) o2;

                if (!c2.isCompleted() && c1.isCompleted()) {
                    return 1;
                }
                if (c2.isCompleted() && !c1.isCompleted()) {
                    return -1;
                }
                return (int) (c2.getId() - c1.getId());
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

    public void setOnClickItemListener(OnClickListener<BaseModel> onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
        updateRenderers();
    }

    public void setOnModelChangedListener(OnModelChangedListener<BaseModel> onModelChangedListener) {
        this.onModelChangedListener = onModelChangedListener;
        updateRenderers();
    }
}
