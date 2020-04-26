package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;

public class ButtonHidingHolder extends BaseHolder<ButtonHidingModel> {

    private View view;
    private TextView text;
    private TextView count;
    private CheckBox checkBox;

    public ButtonHidingHolder(@NonNull View itemView,
                              final OnModelChangedListener<ButtonHidingModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        view = itemView;
        text = view.findViewById(R.id.text_state);
        count = view.findViewById(R.id.text_count);
        checkBox = view.findViewById(R.id.checkbox_done);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setChecked(!model.isChecked());
                setCheckedButtonHiding(model.isChecked());
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        text.setText(model.getText());
        count.setText(String.valueOf(model.getCount()));
        checkBox.setChecked(model.isChecked());
        setCheckedButtonHiding(model.isChecked());
    }

    private void setCheckedButtonHiding(boolean checked) {
        checkBox.setChecked(!checked);
        if (!checked) {
            text.setText("Show Сompleted");
        } else {
            text.setText("Hide Сompleted");
        }
    }

}