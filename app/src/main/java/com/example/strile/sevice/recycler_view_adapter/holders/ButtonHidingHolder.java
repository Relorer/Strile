package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;

public class ButtonHidingHolder extends BaseHolder<ButtonHidingModel> {

    private final TextView text;
    private final TextView count;
    private final CheckBox checkBox;

    public ButtonHidingHolder(@NonNull View itemView,
                              @NonNull final OnModelChangedListener<ButtonHidingModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        text = view.findViewById(R.id.text_state);
        count = view.findViewById(R.id.text_count);
        checkBox = view.findViewById(R.id.checkbox_done);
        view.setOnClickListener(v -> {
            ButtonHidingModel chModel = model.setState(!model.isChecked());
            onModelChangedListener.onChanged(chModel);
        });
    }

    @Override
    public void bind(ButtonHidingModel model) {
        super.bind(model);
        count.setText(String.valueOf(model.getCount()));
        checkBox.setChecked(model.isChecked());
        setCheckedButtonHiding(model.isChecked());
    }

    private void setCheckedButtonHiding(boolean checked) {
        checkBox.setChecked(!checked);
        if (!checked) {
            text.setText(R.string.show_completed);
        } else {
            text.setText(R.string.hide_completed);
        }
    }

}