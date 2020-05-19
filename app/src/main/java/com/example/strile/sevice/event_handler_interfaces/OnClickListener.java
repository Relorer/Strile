package com.example.strile.sevice.event_handler_interfaces;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public interface OnClickListener<T extends BaseModel> {
    void onClick(T sender);
}
