package com.example.strile.sevice.call_back_interfaces;

import androidx.lifecycle.LiveData;

import com.example.strile.database.entities.Case;

import java.util.List;

public interface CompleteLoadCallback {
    void onComplete(LiveData<?> list);
}
