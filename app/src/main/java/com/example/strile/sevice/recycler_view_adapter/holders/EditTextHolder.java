package com.example.strile.sevice.recycler_view_adapter.holders;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.event_handler_interfaces.OnTextChangeListener;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;

public class EditTextHolder extends BaseHolder<EditTextModel> {

    private TextView text;

    public EditTextHolder(@NonNull View itemView, final OnModelChangedListener<EditTextModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        text = itemView.findViewById(R.id.editText);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setText(s.toString());
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        text.setHint(model.getHint());
        changeText();

        if (model.getLineCount() != 0) text.setMaxLines(model.getLineCount());
        text.setMaxLines(Integer.MAX_VALUE);

        if (model.getMaxLength() != 0) {
            text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(model.getMaxLength())});
        }
        else text.setFilters(new InputFilter[]{});
    }

    private void changeText() {
        text.setText(model.getText());
    }
}
