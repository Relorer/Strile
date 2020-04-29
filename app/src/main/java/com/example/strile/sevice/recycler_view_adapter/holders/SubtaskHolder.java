package com.example.strile.sevice.recycler_view_adapter.holders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

public class SubtaskHolder extends BaseHolder<SubtaskModel> {

    private View view;
    private CheckBox state;
    private EditText text;
    private View buttonDelete;

    public SubtaskHolder(@NonNull View itemView, final OnModelChangedListener<SubtaskModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        view = itemView;
        state = view.findViewById(R.id.checkbox_done);
        text = view.findViewById(R.id.editText_subtask);
        buttonDelete = view.findViewById(R.id.image_cross);

        state.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isBinding()) {
                model.setComplete(isChecked);
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }
        });
        buttonDelete.setOnClickListener(v -> {
            text.clearFocus();
            model.setDying(true);
            if (onModelChangedListener != null)
                onModelChangedListener.onChanged(model);
        });
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.setText(s.toString());
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        text.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) buttonDelete.setVisibility(View.VISIBLE);
            else buttonDelete.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        buttonDelete.setVisibility(View.INVISIBLE);
        text.setText(model.getText());
        state.setChecked(model.isComplete());
    }
}
