package com.example.strile.service.recycler_view_adapter.items.subtask;

import android.media.MediaPlayer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;

public class SubtaskHolder extends BaseHolder<SubtaskModel> {

    private final CheckBox state;
    private final EditText text;
    private final View buttonDelete;

    private boolean binding = false;

    private final MediaPlayer checked;

    public SubtaskHolder(@NonNull View itemView,
                         @NonNull OnModelChangedListener<SubtaskModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        state = view.findViewById(R.id.image_icon_settings);
        text = view.findViewById(R.id.editText_subtask);
        buttonDelete = view.findViewById(R.id.image_cross);

        checked = MediaPlayer.create(itemView.getContext(), R.raw.hero_simple_celebration);

        state.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!binding) {
                onModelChangedListener.onChanged(model.setState(isChecked));
                if (isChecked) checked.start();
            }
        });

        buttonDelete.setOnClickListener(v -> {
            text.clearFocus();
            model.setDying(true);
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
        binding = true;
        super.bind(model);
        buttonDelete.setVisibility(View.INVISIBLE);
        text.setText(model.getText());
        state.setChecked(model.isComplete());
        binding = false;
    }
}
