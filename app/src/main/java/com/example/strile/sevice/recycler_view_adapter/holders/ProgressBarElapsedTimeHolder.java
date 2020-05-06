package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;

public class ProgressBarElapsedTimeHolder extends BaseHolder<ProgressBarElapsedTimeModel> {

    private final ProgressBar progressBar;
    private final TextView text;

    public ProgressBarElapsedTimeHolder(@NonNull View itemView,
                                        @NonNull OnModelChangedListener<ProgressBarElapsedTimeModel> onModelChangedListener,
                                        @NonNull OnClickListener<ProgressBarElapsedTimeModel> onClickListener) {
        super(itemView, onModelChangedListener);
        progressBar = view.findViewById(R.id.progressBar_elapsedTime);
        text = view.findViewById(R.id.text_elapsedTime);
        view.setOnClickListener(v -> {
                onClickListener.onClick(model);
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(ProgressBarElapsedTimeModel model) {
        super.bind(model);
        text.setText(String.format("%d/%d %s", model.getProgress(), model.getMax(),
                view.getContext().getString(R.string.minutes)));
        progressBar.setProgress(model.getProgress());
        progressBar.setMax(model.getMax());
    }
}