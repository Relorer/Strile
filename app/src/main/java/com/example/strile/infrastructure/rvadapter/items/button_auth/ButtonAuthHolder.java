package com.example.strile.infrastructure.rvadapter.items.button_auth;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.utilities.events.OnClickListener;
import com.example.strile.utilities.events.OnModelChangedListener;

public class ButtonAuthHolder extends BaseHolder<ButtonAuthModel> {

    private final TextView name;

    public ButtonAuthHolder(@NonNull View itemView,
                                  @NonNull final OnModelChangedListener<ButtonAuthModel> onModelChangedListener,
                                  @NonNull final OnClickListener<BaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        name = itemView.findViewById(R.id.text_name);

        itemView.setOnClickListener(v -> onClickListener.onClick(model));
    }

    @Override
    public void bind(ButtonAuthModel model) {
        super.bind(model);
        name.setText(model.getText());
    }
}