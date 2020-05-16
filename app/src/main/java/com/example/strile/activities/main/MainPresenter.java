package com.example.strile.activities.main;

import com.example.strile.sevice.progress.KeeperHistoryExecutions;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.progress.Person;

public class MainPresenter extends BasePresenter<MainActivity> {

    private Person person;
    private KeeperHistoryExecutions keeper;

    @Override
    protected void updateView() {
        person = new Person(view(), 0);
        keeper = KeeperHistoryExecutions.getInstance(view(), person);
    }

    public void bottomNavigationSelectedItemChanged(int selectedID) {
    }
}
