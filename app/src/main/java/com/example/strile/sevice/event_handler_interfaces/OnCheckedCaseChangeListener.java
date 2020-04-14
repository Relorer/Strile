package com.example.strile.sevice.event_handler_interfaces;

import com.example.strile.database.entities.Case;

public interface OnCheckedCaseChangeListener {
    void onCheckedChanged(Case c, boolean isChecked);
}
