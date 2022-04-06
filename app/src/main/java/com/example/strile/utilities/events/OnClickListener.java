package com.example.strile.utilities.events;

import com.example.strile.infrastructure.rvadapter.items.BaseModel;

public interface OnClickListener<T extends BaseModel> {
    void onClick(T sender);
}
