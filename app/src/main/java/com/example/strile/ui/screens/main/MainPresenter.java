package com.example.strile.ui.screens.main;

import com.example.strile.R;
import com.example.strile.infrastructure.presenter.BasePresenter;

public class MainPresenter extends BasePresenter<MainActivity> {

    private int itemId = R.id.item_journal;

    @Override
    protected void updateView() {
        view().setVisibleFragment(itemId);
    }

    public void bottomNavigationSelectedItemChanged(int selectedID) {
        itemId = selectedID;
    }
}