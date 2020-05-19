package com.example.strile.sevice.event_handler_interfaces;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public interface OnModelChangedListener<T extends BaseModel> {
    void onChanged(T model);
}