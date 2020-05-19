package com.example.strile.sevice.recycler_view_adapter.items.edit_text;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;

public class EditTextHolder extends BaseHolder<EditTextModel> {

    private final TextView text;

    public EditTextHolder(@NonNull View itemView,
                          @NonNull final OnModelChangedListener<EditTextModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        text = itemView.findViewById(R.id.editText);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onModelChangedListener.onChanged(model.setText(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void bind(EditTextModel model) {
        super.bind(model);
        text.setHint(model.getHint());
        text.setText(model.getText());
        if (model.getLineCount() != 0) text.setMaxLines(model.getLineCount());
        else text.setMaxLines(Integer.MAX_VALUE);

        if (model.getLineCount() == 1) {
            text.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        } else {
            text.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                    InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        }

        if (model.getMaxLength() != 0) {
            text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(model.getMaxLength())});
        } else text.setFilters(new InputFilter[]{});
    }
}
