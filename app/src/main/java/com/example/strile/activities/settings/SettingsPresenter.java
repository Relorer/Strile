package com.example.strile.activities.settings;

import com.example.strile.sevice.presenter.BasePresenter;

public class SettingsPresenter extends BasePresenter<SettingsActivity> {
    @Override
    protected void updateView() {

    }

    public void backButtonClicked() {
        view().finish();
    }
}
