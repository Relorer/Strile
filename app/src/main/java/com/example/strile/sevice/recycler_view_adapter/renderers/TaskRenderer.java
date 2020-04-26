package com.example.strile.sevice.recycler_view_adapter.renderers;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.R;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.holders.TaskHolder;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public class TaskRenderer extends BaseRenderer<CaseModel, TaskHolder> {

    private OnClickListener onClickListener;

    public TaskRenderer(OnModelChangedListener onModelChangedListener, OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TaskHolder createViewHolder(@Nullable ViewGroup parent) {
        return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_journal, parent, false), onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.TASK_TYPE;
    }
}
