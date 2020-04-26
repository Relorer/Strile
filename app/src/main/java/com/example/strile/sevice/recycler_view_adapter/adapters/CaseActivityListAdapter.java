package com.example.strile.sevice.recycler_view_adapter.adapters;

import androidx.recyclerview.widget.SortedList;

import com.example.strile.sevice.event_handler_interfaces.OnCheckedChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.event_handler_interfaces.OnSeekBarChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnTextChangeListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.renderers.ButtonAddSubtaskRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.ButtonDateSelectionRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.ButtonRepeatRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.ButtonTimeGoalRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.CurrentStreakRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.EditTextRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.ProgressBarElapsedTimeRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.SeekBarDifficultRenderer;
import com.example.strile.sevice.recycler_view_adapter.renderers.SubtaskRenderer;

public class CaseActivityListAdapter extends BaseRecyclerViewAdapter {

    private OnClickListener<BaseModel> onClickItemListener;
    private OnModelChangedListener<BaseModel> onModelChangedListener;

    @Override
    protected SortedList<BaseModel> getSortedItems() {
        return new SortedList<>(BaseModel.class, new SortedList.Callback<BaseModel>() {
            @Override
            public int compare(BaseModel o1, BaseModel o2) {
                if (o1 instanceof SubtaskModel && o2 instanceof SubtaskModel) return o1.getId() - o2.getId();
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

    protected void updateRenderers() {
        super.updateRenderers();
        registerRenderer(new SubtaskRenderer(onModelChangedListener));
        registerRenderer(new SeekBarDifficultRenderer(onModelChangedListener));
        registerRenderer(new EditTextRenderer(onModelChangedListener));
        registerRenderer(new ButtonDateSelectionRenderer(onModelChangedListener));
        registerRenderer(new ButtonAddSubtaskRenderer(onModelChangedListener, onClickItemListener));
        registerRenderer(new ButtonRepeatRenderer(onModelChangedListener));
        registerRenderer(new ButtonTimeGoalRenderer(onModelChangedListener));
        registerRenderer(new ProgressBarElapsedTimeRenderer(onModelChangedListener));
        registerRenderer(new CurrentStreakRenderer(onModelChangedListener));
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
