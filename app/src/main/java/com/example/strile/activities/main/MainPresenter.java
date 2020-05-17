package com.example.strile.activities.main;

import com.example.strile.R;
import com.example.strile.sevice.presenter.BasePresenter;

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