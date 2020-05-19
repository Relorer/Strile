package com.example.strile.sevice.presenter;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {
    private WeakReference<V> view;

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    protected abstract void updateView();

    private boolean setupDone() {
        return view() != null;
    }
}
