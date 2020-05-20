package com.example.strile.service.event_handler_interfaces;

import com.example.strile.service.recycler_view_adapter.items.BaseModel;

public interface OnModelChangedListener<T extends BaseModel> {
    void onChanged(T model);
}