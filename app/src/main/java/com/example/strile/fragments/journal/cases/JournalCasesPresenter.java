package com.example.strile.fragments.journal.cases;

import com.example.strile.sevice.Day;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.models.CaseModel;

import java.util.Date;

public abstract class JournalCasesPresenter<T extends JournalCasesFragment> extends BasePresenter<T> {

    protected Date visibleDay = new Day(new Date()).getDateOfDayWithoutTime();

    public void setVisibleDay(Date visibleDay) {
        this.visibleDay = visibleDay;
    }

    public abstract void itemClicked(CaseModel model);

    public abstract void itemStateChanged(CaseModel model);

    public abstract void buttonHidingStateChanged(ButtonHidingModel button);
}