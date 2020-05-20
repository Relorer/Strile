package com.example.strile.service.recycler_view_adapter.items.executed;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;

public class ExecutedHolder extends BaseHolder<ExecutedModel> {

    private final TextView name;
    private final TextView experience;

    public ExecutedHolder(@NonNull View itemView, OnModelChangedListener<ExecutedModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        name = view.findViewById(R.id.text_name);
        experience = view.findViewById(R.id.text_info);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(ExecutedModel model) {
        super.bind(model);
        name.setText(model.getName());
        experience.setText(String.format("%d exp", model.getExperience()));
    }
}
