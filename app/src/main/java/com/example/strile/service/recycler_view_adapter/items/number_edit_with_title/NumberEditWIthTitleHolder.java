package com.example.strile.service.recycler_view_adapter.items.number_edit_with_title;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;

public class NumberEditWIthTitleHolder extends BaseHolder<NumberEditWIthTitleModel> {

    private final TextView title;
    private final TextView info;
    private final SeekBar number;

    public NumberEditWIthTitleHolder(@NonNull View itemView, @NonNull OnModelChangedListener<NumberEditWIthTitleModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        title = itemView.findViewById(R.id.text_title);
        info = itemView.findViewById(R.id.text_info);
        number = itemView.findViewById(R.id.seekBar_number);

        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    onModelChangedListener.onChanged(model.setNumber(progress + model.getMin()));
                    info.setText(String.format("%d %s", model.getNumber(), model.getPostfix()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(NumberEditWIthTitleModel model) {
        super.bind(model);
        title.setText(model.getTitle());
        info.setText(String.format("%d %s", model.getNumber(), model.getPostfix()));
        number.setMax(model.getMax() - model.getMin());
        number.setProgress(model.getNumber() - model.getMin());
    }
}
