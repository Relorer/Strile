package com.example.strile.fragments.journal.cases;

import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.models.CaseModel;

public abstract class JournalCasesPresenter extends BasePresenter<JournalCasesFragment> {

    public abstract void itemClicked(CaseModel model);

    public abstract void itemStateChanged(CaseModel model);

    public abstract void buttonHidingStateChanged(ButtonHidingModel button);

}