package com.example.strile.sevice.call_back_interfaces;

import androidx.lifecycle.LiveData;

public interface CompleteLoadCallback {
    void onComplete(LiveData<?> list);
}
