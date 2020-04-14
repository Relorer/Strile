package com.example.strile.fragments.journal;

import android.util.Log;

import com.example.strile.sevice.presenter.BasePresenter;

public class JournalPresenter extends BasePresenter<Boolean, JournalFragment> {

    JournalPresenter() {
        model = true;
    }

    @Override
    protected void updateView() {

    }

    void addButtonClicked(String nameList) {
        if (nameList != null) {
            if(nameList.equals("Habits")) view().startAddHabitFragment();
            else view().startAddTaskFragment();
        }
    }
}
