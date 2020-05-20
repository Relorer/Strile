package com.example.strile.service.event_handler_interfaces;

import com.example.strile.service.recycler_view_adapter.items.BaseModel;

public interface OnClickListener<T extends BaseModel> {
    void onClick(T sender);
}
