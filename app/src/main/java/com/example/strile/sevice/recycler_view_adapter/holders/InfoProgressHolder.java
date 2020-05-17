package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.InfoProgressModel;

public class InfoProgressHolder extends BaseHolder<InfoProgressModel> {

    private final TextView level;
    private final TextView remained;
    private final ProgressBar levelProgress;

    public InfoProgressHolder(@NonNull View itemView,
                              @NonNull OnModelChangedListener<InfoProgressModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        level = view.findViewById(R.id.text_level);
        remained = view.findViewById(R.id.text_count_remaining_points);
        levelProgress = view.findViewById(R.id.progressBar_level);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(InfoProgressModel model) {
        super.bind(model);
        level.setText(String.valueOf(model.getLevel()));
        remained.setText(String.format("%d %s", model.getRemained(),
                view.getContext().getString(R.string.points_more)));
        levelProgress.setMax((int) (model.getRemained() + model.getExperience()));
        levelProgress.setProgress((int) model.getExperience());
    }
}