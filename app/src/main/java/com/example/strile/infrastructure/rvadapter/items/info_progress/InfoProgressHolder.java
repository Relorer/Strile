package com.example.strile.infrastructure.rvadapter.items.info_progress;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;

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
        int remainedCount = (int) model.getRemained();
        remained.setText(view.getContext().getResources().getQuantityString(R.plurals.points_more, remainedCount, remainedCount));
        levelProgress.setMax((int) (model.getRemained() + model.getExperience()));
        levelProgress.setProgress((int) model.getExperience());
    }
}