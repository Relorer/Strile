package com.example.strile.sevice.call_back_interfaces;

import android.view.View;

public interface ShowSnackbarCallback {
    void show(String text, String actionName, View.OnClickListener onClickListener);
}
