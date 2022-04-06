package com.example.strile.utilities.callbacks;

import android.view.View;

public interface ShowSnackbarCallback {
    void show(String text, String actionName, View.OnClickListener onClickListener);
}
