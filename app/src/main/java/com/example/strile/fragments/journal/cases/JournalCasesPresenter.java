package com.example.strile.fragments.journal.cases;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.strile.database.entities.CaseModel;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.database.models.CaseDatabaseModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public abstract class JournalCasesPresenter extends BasePresenter<CaseDatabaseModel, JournalCasesFragment> {

    private LiveData<List<CaseModel>> cases;

    private List<CaseModel> full;
    private List<CaseModel> incomplete;
    private List<CaseModel> forDay;
    private ButtonHidingModel button;

    public JournalCasesPresenter() {
        model = getModel();
        model.loadCases(list -> {
            cases = (LiveData<List<CaseModel>>) list;
            updateView();
        });
        button = new ButtonHidingModel();
        DateManager.addOnChangeVisibleDayListener(model -> {
            if (setupDone())
                updateSortedListOnScreen();
        });
    }

    protected abstract CaseDatabaseModel getModel();

    @Override
    protected void updateView() {
        if (cases != null) {
            cases.removeObservers(view());
            cases.observe(view(), caseModels -> {
                full = caseModels;
                updateSortedListOnScreen();
            });
        }
    }

    void itemClicked(CaseModel c) {
        view().startCaseActivity(c);
    }

    void itemStateChanged(CaseModel c) {
        updateCase(c);
    }

    void buttonHidingStateChanged(ButtonHidingModel button) {
        updateSortedListOnScreen();
    }

    private void updateSortedListOnScreen() {
        forDay = chooseForDay(full);
        incomplete = chooseIncomplete(forDay);
        button.setCount(forDay.size() - incomplete.size());
        if (setupDone()) {
            ArrayList<BaseModel> items = new ArrayList<>();
            if (button.isChecked()) items.addAll(forDay);
            else items.addAll(incomplete);
            if (button.getCount() > 0) {
                items.add(button);
            }
            view().setSortedList(items);
        }
//        for(CaseModel model : full) {
//                model.visibleDayChanged();
//        }
    }

    private void updateCase(CaseModel c) {
        model.updateCase(c, null);
    }

    private List<CaseModel> chooseIncomplete(List<CaseModel> caseModels) {
        List<CaseModel> chosen = new ArrayList<>();
        for (CaseModel c : caseModels) {
            if (!c.isCompleted()) chosen.add(c);
        }
        return chosen;
    }

    private List<CaseModel> chooseForDay(List<CaseModel> caseModels) {
        List<CaseModel> chosen = new ArrayList<>();
        for (CaseModel c : caseModels) {
            if (c.plannedForDay()) chosen.add(c);
        }
        return chosen;
    }
}