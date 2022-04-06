package com.example.strile.utilities.callbacks;

import javax.security.auth.callback.Callback;

public interface CompleteCallback<T> extends Callback {
    void onComplete(T result);
}
