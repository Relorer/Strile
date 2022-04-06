package com.example.strile.infrastructure.rvadapter.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.SortedList;

import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_add_subtask.ButtonAddSubtaskRenderer;
import com.example.strile.infrastructure.rvadapter.items.button_date_selection.ButtonDateSelectionRenderer;
import com.example.strile.infrastructure.rvadapter.items.button_repeat.ButtonRepeatRenderer;
import com.example.strile.infrastructure.rvadapter.items.button_time_goal.ButtonTimeGoalRenderer;
import com.example.strile.infrastructure.rvadapter.items.button_time_selection.ButtonTimeSelectionRenderer;
import com.example.strile.infrastructure.rvadapter.items.current_streak.CurrentStreakRenderer;
import com.example.strile.infrastructure.rvadapter.items.edit_text.EditTextRenderer;
import com.example.strile.infrastructure.rvadapter.items.progress_bar_elapsed_time.ProgressBarElapsedTimeRenderer;
import com.example.strile.infrastructure.rvadapter.items.seek_bar_difficult.SeekBarDifficultRenderer;
import com.example.strile.infrastructure.rvadapter.items.subtask.SubtaskModel;
import com.example.strile.infrastructure.rvadapter.items.subtask.SubtaskRenderer;

public class CaseActivityListAdapter extends BaseRecyclerViewAdapter {

    public CaseActivityListAdapter(@NonNull OnClickListener<BaseModel> onClickItemListener,
                                   @NonNull OnModelChangedListener<BaseModel> onModelChangedListener) {
        registerRenderer(new SubtaskRenderer(onModelChangedListener));
        registerRenderer(new SeekBarDifficultRenderer(onModelChangedListener));
        registerRenderer(new EditTextRenderer(onModelChangedListener));
        registerRenderer(new ButtonDateSelectionRenderer(onModelChangedListener));
        registerRenderer(new ButtonAddSubtaskRenderer(onModelChangedListener, onClickItemListener));
        registerRenderer(new ButtonRepeatRenderer(onModelChangedListener));
        registerRenderer(new ButtonTimeGoalRenderer(onModelChangedListener));
        registerRenderer(new ButtonTimeSelectionRenderer(onModelChangedListener));
        registerRenderer(new ProgressBarElapsedTimeRenderer(onModelChangedListener, onClickItemListener));
        registerRenderer(new CurrentStreakRenderer(onModelChangedListener));
    }

    @Override
    protected SortedList<BaseModel> getSortedItems() {
        return new SortedList<>(BaseModel.class, new SortedList.Callback<BaseModel>() {
            @Override
            public int compare(BaseModel o1, BaseModel o2) {
                if (o1 instanceof SubtaskModel && o2 instanceof SubtaskModel)
                    return o1.getId() - o2.getId();
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(BaseModel oldItem, BaseModel newItem) {
                return oldItem.getId() == newItem.getId() && oldItem.equals(newItem);
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
