package com.example.strile.service.recycler_view_adapter.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.SortedList;

import com.example.strile.service.event_handler_interfaces.OnClickListener;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.button_hiding.ButtonHidingModel;
import com.example.strile.service.recycler_view_adapter.items.button_hiding.ButtonHidingRenderer;
import com.example.strile.service.recycler_view_adapter.items.habit.HabitModel;
import com.example.strile.service.recycler_view_adapter.items.habit.HabitRenderer;
import com.example.strile.service.recycler_view_adapter.items.task.TaskModel;
import com.example.strile.service.recycler_view_adapter.items.task.TaskRenderer;

public class JournalListAdapter extends BaseRecyclerViewAdapter {

    public JournalListAdapter(@NonNull OnClickListener<BaseModel> onClickItemListener,
                              @NonNull OnModelChangedListener<BaseModel> onModelChangedListener) {
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
                if (o1 instanceof HabitModel && o2 instanceof HabitModel) {
                    final HabitModel hm1 = (HabitModel) o1;
                    final HabitModel hm2 = (HabitModel) o2;
                    if (!hm2.isComplete() && hm1.isComplete()) return 1;
                    if (hm2.isComplete() && !hm1.isComplete()) return -1;
                } else if (o1 instanceof TaskModel && o2 instanceof TaskModel) {
                    final TaskModel tm1 = (TaskModel) o1;
                    final TaskModel tm2 = (TaskModel) o2;
                    if (!tm2.isComplete() && tm1.isComplete()) return 1;
                    if (tm2.isComplete() && !tm1.isComplete()) return -1;
                    if (tm1.getDeadline() == 0 && tm2.getDeadline() != 0) return 1;
                    if (tm1.getDeadline() != 0 && tm2.getDeadline() == 0) return -1;
                    return (int) (tm1.getDeadline() - tm2.getDeadline());
                }
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
