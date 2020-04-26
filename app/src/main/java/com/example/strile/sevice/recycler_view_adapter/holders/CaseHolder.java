package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

public abstract class CaseHolder extends BaseHolder<CaseModel> {

    private OnClickListener<CaseModel> onClickListener;

    protected View view;

    protected CheckBox done;
    protected TextView name;
    protected TextView info;
    protected ImageView special;

    public CaseHolder(@NonNull View itemView,
                      final OnModelChangedListener<CaseModel> onModelChangedListener,
                      final OnClickListener<CaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        this.onClickListener = onClickListener;
        done = itemView.findViewById(R.id.checkbox_done);
        name = itemView.findViewById(R.id.text_name);
        info = itemView.findViewById(R.id.text_info);
        special = itemView.findViewById(R.id.image_special);
        view = itemView;
    }

    @Override
    protected void _bind() {
        super._bind();
        name.setText(model.getName());
        changeStateDone();
    }

    protected void changeStateDone() {
        done.setChecked(model.isCompleted());
    }
}