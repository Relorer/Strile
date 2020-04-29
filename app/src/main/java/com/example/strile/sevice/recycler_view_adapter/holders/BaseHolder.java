package com.example.strile.sevice.recycler_view_adapter.holders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public abstract class BaseHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    protected OnModelChangedListener onModelChangedListener;

    protected M model;

    protected View view;

    private boolean binding;

    public BaseHolder(@NonNull View itemView, OnModelChangedListener<M> onModelChangedListener) {
        super(itemView);
        this.onModelChangedListener = onModelChangedListener;
        this.view = itemView;
    }

    public void bind(M model) {
        binding = true;
        this.model = model;
        _bind();
        binding = false;
        model.setOnChangeListener(model1 -> {binding = true; _bind(); binding = false;});
    }

    protected void _bind() {
        if (model.isTopMargin()) setTopMargin(view.getResources().getDimensionPixelSize(R.dimen.margin_between_blocks));
        else setTopMargin(0);
    }

    protected boolean isBinding() {
        return binding;
    }

    private void setTopMargin(int top) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(0, top, 0, 0);
            view.requestLayout();
        }
    }
}
