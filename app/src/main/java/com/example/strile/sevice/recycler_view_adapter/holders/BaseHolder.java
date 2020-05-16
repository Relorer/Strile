package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public abstract class BaseHolder<M extends BaseModel> extends RecyclerView.ViewHolder {

    protected final OnModelChangedListener onModelChangedListener;
    protected final View view;
    protected M model;

    public BaseHolder(@NonNull View itemView,
                      @NonNull OnModelChangedListener<M> onModelChangedListener) {
        super(itemView);
        this.onModelChangedListener = onModelChangedListener;
        this.view = itemView;
    }

    public void bind(M model) {
        this.model = model;
        int margin = view.getResources().getDimensionPixelSize(R.dimen.margin_between_blocks);
        if (model.isTopMargin() && model.isBottomMargin()) setMargin(margin, margin);
        else if (model.isTopMargin()) setMargin(margin, 0);
        else if (model.isBottomMargin()) setMargin(0, margin);
        else setMargin(0, 0);
    }

    private void setMargin(int top, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(0, top, 0, bottom);
            view.requestLayout();
        }
    }
}
