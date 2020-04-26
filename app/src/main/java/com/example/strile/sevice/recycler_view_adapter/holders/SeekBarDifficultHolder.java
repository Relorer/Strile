package com.example.strile.sevice.recycler_view_adapter.holders;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.event_handler_interfaces.OnSeekBarChangeListener;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

import static com.example.strile.sevice.DifficultyManager.getColor;


public class SeekBarDifficultHolder extends BaseHolder<SeekBarDifficultModel> {

    private SeekBar seekBar;
    private TextView text;

    public SeekBarDifficultHolder(final @NonNull View itemView,
                                  final OnModelChangedListener<SeekBarDifficultModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        seekBar = itemView.findViewById(R.id.seekBar_difficult);
        text = itemView.findViewById(R.id.text_difficult);
        seekBar.setMax(DifficultyManager.maxDifficult);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                model.setProgress(progress);
                changeTextDifficult();
                changeColorThumb();
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        seekBar.setProgress(model.getProgress());
        changeColorThumb();
        changeTextDifficult();
    }

    private void changeTextDifficult() {
        text.setText(DifficultyManager.getName(model.getProgress()));
        text.setTextColor(itemView.getContext().getColor(getColor(model.getProgress())));
    }

    private void changeColorThumb() {
        Drawable thumb = seekBar.getThumb();
        int difficulty = seekBar.getProgress();
        thumb.setColorFilter(itemView.getContext().getColor(getColor(difficulty)), PorterDuff.Mode.SRC_ATOP);
        seekBar.setThumb(thumb);
    }
}
