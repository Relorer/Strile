package com.example.strile.sevice.recycler_view_adapter.holders;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.Difficulty;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

public class SeekBarDifficultHolder extends BaseHolder<SeekBarDifficultModel> {

    private final SeekBar seekBar;
    private final TextView text;

    public SeekBarDifficultHolder(@NonNull View itemView,
                                  @NonNull OnModelChangedListener<SeekBarDifficultModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        seekBar = itemView.findViewById(R.id.seekBar_difficult);
        text = itemView.findViewById(R.id.text_difficult);
        seekBar.setMax(Difficulty.maxDifficulty);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onModelChangedListener.onChanged(model.setProgress(progress));
                Difficulty difficulty = new Difficulty(progress);
                changeTextDifficult(difficulty);
                changeColorThumb(difficulty);
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
    public void bind(SeekBarDifficultModel model) {
        super.bind(model);
        seekBar.setProgress(model.getProgress());
        final Difficulty difficulty = new Difficulty(model.getProgress());
        changeColorThumb(difficulty);
        changeTextDifficult(difficulty);
    }

    private void changeTextDifficult(Difficulty difficulty) {
        text.setText(difficulty.getName(view.getContext()));
        text.setTextColor(view.getContext().getColor(difficulty.getColor()));
    }

    private void changeColorThumb(Difficulty difficulty) {
        Drawable thumb = seekBar.getThumb();
        thumb.setColorFilter(view.getContext().getColor(difficulty.getColor()), PorterDuff.Mode.SRC_ATOP);
        seekBar.setThumb(thumb);
    }
}
