package com.example.strile.service.call_back_interfaces;

import javax.security.auth.callback.Callback;

public interface CompleteCallback<T> extends Callback {
    void onComplete(T result);
}
