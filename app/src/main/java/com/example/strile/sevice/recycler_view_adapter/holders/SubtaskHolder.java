package com.example.strile.sevice.recycler_view_adapter.holders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

public class SubtaskHolder extends BaseHolder<SubtaskModel> {

    private final CheckBox state;
    private final EditText text;
    private final View buttonDelete;

    public SubtaskHolder(@NonNull View itemView,
                         @NonNull OnModelChangedListener<SubtaskModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        state = view.findViewById(R.id.checkbox_done);
        text = view.findViewById(R.id.editText_subtask);
        buttonDelete = view.findViewById(R.id.image_cross);

        state.setOnCheckedChangeListener((buttonView, isChecked) -> {
            onModelChangedListener.onChanged(model.setState(isChecked));
        });
        buttonDelete.setOnClickListener(v -> {
            text.clearFocus();
            onModelChangedListener.onChanged(model);
        });
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
        text.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) buttonDelete.setVisibility(View.VISIBLE);
            else buttonDelete.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void bind(SubtaskModel model) {
        super.bind(model);
        buttonDelete.setVisibility(View.INVISIBLE);
        text.setText(model.getText());
        state.setChecked(model.isComplete());
    }
}
