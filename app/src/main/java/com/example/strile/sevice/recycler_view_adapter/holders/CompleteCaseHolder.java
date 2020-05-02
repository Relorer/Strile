package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.database.entities.CompleteCaseModel;

public class CompleteCaseHolder extends BaseHolder<CompleteCaseModel> {

    private TextView name;
    private TextView experience;

    public CompleteCaseHolder(@NonNull View itemView, OnModelChangedListener<CompleteCaseModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        name = view.findViewById(R.id.text_name);
        experience = view.findViewById(R.id.text_info);
    }

    @Override
    protected void _bind() {
        super._bind();
        name.setText(model.getName());
        experience.setText(String.format("%d exp", model.getExperience()));
    }
}
