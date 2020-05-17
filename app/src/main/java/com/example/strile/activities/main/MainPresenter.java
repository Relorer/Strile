package com.example.strile.activities.main;

import com.example.strile.R;
import com.example.strile.sevice.progress.KeeperHistoryExecutions;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.progress.Progress;

public class MainPresenter extends BasePresenter<MainActivity> {

    private KeeperHistoryExecutions keeper;
    private int itemId = R.id.item_journal;

    @Override
    protected void updateView() {
        Progress progress = Progress.getInstance(view());
        keeper = KeeperHistoryExecutions.getInstance(view(), progress);
        view().setVisibleFragment(itemId);
    }

    public void bottomNavigationSelectedItemChanged(int selectedID) {
        itemId = selectedID;
    }
}