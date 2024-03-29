package com.example.strile.infrastructure.rvadapter.items.task;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.BaseRenderer;

public class TaskRenderer extends BaseRenderer<TaskModel, TaskHolder> {

    private final OnClickListener onClickListener;

    public TaskRenderer(@NonNull OnModelChangedListener onModelChangedListener,
                        @NonNull OnClickListener onClickListener) {
        super(onModelChangedListener);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TaskHolder createViewHolder(@NonNull ViewGroup parent) {
        return new TaskHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_journal, parent, false),
                onModelChangedListener, onClickListener);
    }

    @Override
    public int getType() {
        return BaseModel.TASK_TYPE;
    }
}
