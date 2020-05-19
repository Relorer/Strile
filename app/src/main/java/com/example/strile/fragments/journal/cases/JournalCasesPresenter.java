package com.example.strile.fragments.journal.cases;

import com.example.strile.sevice.date.Day;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.items.CaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.button_hiding.ButtonHidingModel;

import java.util.Date;

public abstract class JournalCasesPresenter<T extends JournalCasesFragment> extends BasePresenter<T> {

    protected boolean showCompleted = false;

    protected final int idButton;

    protected Date visibleDay = Day.getDateOfDayWithoutTime(new Date());

    protected JournalCasesPresenter() {
        idButton = new ButtonHidingModel(false, false, 0).getId();
    }

    public void setVisibleDay(Date visibleDay) {
        this.visibleDay = visibleDay;
    }

    public abstract void itemClicked(CaseModel model);

    public abstract void itemStateChanged(CaseModel model);

    public void buttonHidingStateChanged(ButtonHidingModel button) {
        showCompleted = button.isChecked();
    }
}