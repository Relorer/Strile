package com.example.strile.activities.main;

import android.util.Log;

import com.example.strile.R;
import com.example.strile.sevice.presenter.BasePresenter;

public class MainPresenter extends BasePresenter<Boolean, MainActivity> {

    public MainPresenter() {
        model = true;
    }

    @Override
    protected void updateView() {

    }

    public void bottomNavigationSelectedItemChanged(int selectedID) {
    }
}
