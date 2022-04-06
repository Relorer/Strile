package com.example.strile.ui.screens.main.journal.cases;

import com.example.strile.utilities.extensions.DateUtilities;
import com.example.strile.infrastructure.presenter.BasePresenter;
import com.example.strile.infrastructure.rvadapter.items.CaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_hiding.ButtonHidingModel;

import java.util.Date;

public abstract class JournalCasesPresenter<T extends JournalCasesFragment> extends BasePresenter<T> {

    protected final int idButton;
    protected boolean showCompleted = false;
    protected Date visibleDay = DateUtilities.getDateOfDayWithoutTime(new Date());

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