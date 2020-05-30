package com.example.strile.service.recycler_view_adapter.items.button_add_subtask;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnClickListener;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;

public class ButtonAddSubtaskHolder extends BaseHolder<ButtonAddSubtaskModel> {

    public ButtonAddSubtaskHolder(@NonNull View itemView,
                                  @NonNull final OnModelChangedListener<ButtonAddSubtaskModel> onModelChangedListener,
                                  @NonNull final OnClickListener<BaseModel> onClickListener) {
        super(itemView, onModelChangedListener);
        ImageView icon = itemView.findViewById(R.id.image_icon);
        TextView name = itemView.findViewById(R.id.text_name);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.plus, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
        name.setText(R.string.add_subtasks);

        itemView.setOnClickListener(v -> onClickListener.onClick(model));
    }
}