package com.example.strile.utilities.events;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public interface OnModelChangedListener<T extends BaseModel> {
    void onChanged(T model);
}