package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;

public class ProgressBarElapsedTimeHolder extends BaseHolder<ProgressBarElapsedTimeModel> {

    private ProgressBar progressBar;
    private TextView text;

    public ProgressBarElapsedTimeHolder(@NonNull View itemView,
                                        OnModelChangedListener<ProgressBarElapsedTimeModel> onModelChangedListener,
                                        OnClickListener<ProgressBarElapsedTimeModel> onClickListener) {
        super(itemView, onModelChangedListener);
        progressBar = view.findViewById(R.id.progressBar_elapsedTime);
        text = view.findViewById(R.id.text_elapsedTime);
        view.setOnClickListener(v -> {
            if (onClickListener != null)
                onClickListener.onClick(model);
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        text.setText(model.getProgress() + "/" + model.getMax() + " minutes");
        progressBar.setProgress(model.getProgress());
        progressBar.setMax(model.getMax());
    }
}