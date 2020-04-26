package com.example.strile.sevice.event_handler_interfaces;

import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public interface OnClickListener<T extends BaseModel> {
    public void onClick(T sender);
}
