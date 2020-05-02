package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.InfoProgressModel;

public class InfoProgressHolder extends BaseHolder<InfoProgressModel> {

    private TextView name;
    private TextView days;
    private TextView level;
    private TextView remained;
    private ProgressBar levelProgress;

    public InfoProgressHolder(@NonNull View itemView, OnModelChangedListener<InfoProgressModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        name = view.findViewById(R.id.text_name_person);
        days = view.findViewById(R.id.text_count_active_days);
        level = view.findViewById(R.id.text_level);
        remained = view.findViewById(R.id.text_count_remaining_points);
        levelProgress = view.findViewById(R.id.progressBar_level);
    }

    @Override
    protected void _bind() {
        super._bind();
        name.setText(model.getName());
        days.setText(String.format("%d days of active", model.getDays()));
        level.setText(String.valueOf(model.getLevel()));
        remained.setText(String.format("%d points more", model.getRemained()));
        levelProgress.setMax((int) (model.getRemained()));
        levelProgress.setProgress((int) model.getExperience());
    }
}
