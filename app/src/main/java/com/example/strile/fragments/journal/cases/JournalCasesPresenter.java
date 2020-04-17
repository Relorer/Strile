package com.example.strile.fragments.journal.cases;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.strile.database.entities.Case;
import com.example.strile.database.models.CaseModel;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.ItemModel;

import java.util.ArrayList;
import java.util.List;

public abstract class JournalCasesPresenter extends BasePresenter<CaseModel, JournalCasesFragment> {

    private LiveData<List<Case>> cases;
    private List<Case> incomplete;
    private List<Case> forToday;
    ButtonHidingModel button;

    public JournalCasesPresenter() {
        model = getModel();
        model.loadCases(new CompleteLoadCallback() {
            @Override
            public void onComplete(LiveData<?> list) {
                cases = (LiveData<List<Case>>) list;
                updateView();
            }
        });
        button = new ButtonHidingModel();
    }

    protected abstract CaseModel getModel();

    @Override
    protected void updateView() {
        if (cases != null) {
            cases.removeObservers(view());
            cases.observe(view(), new Observer<List<Case>>() {
                @Override
                public void onChanged(List<Case> cases) {
                    forToday = chooseForToday(cases);
                    incomplete = chooseIncomplete(forToday);
                    button.setCount(forToday.size() - incomplete.size());
                    updateSortedListOnScreen();
                }
            });
        }
    }

    void itemClicked(Case c) {
        view().startCaseActivity(c);
    }

    void itemStateChanged(Case c) {
        updateCase(c);
    }

    void buttonHidingStateChanged(ButtonHidingModel button) {
        updateSortedListOnScreen();
    }

    private void updateSortedListOnScreen() {
        ArrayList<ItemModel> items;
        if (button.isChecked()) items = new ArrayList<ItemModel>(forToday);
        else items = new ArrayList<ItemModel>(incomplete);
        if (button.getCount() > 0) {
            items.add(button);
        }
        view().setSortedList(items);
    }

    private void updateCase(Case c) {
        model.updateCase(c, null);
    }

    private List<Case> chooseIncomplete(List<Case> cases) {
        List<Case> chosen = new ArrayList<>();
        for (Case c : cases) {
            if (!c.completed()) chosen.add(c);
        }
        return chosen;
    }

    private List<Case> chooseCompleted(List<Case> cases) {
        List<Case> chosen = new ArrayList<>();
        for (Case c : cases) {
            if (c.completed()) chosen.add(c);
        }
        return chosen;
    }

    private List<Case> chooseForToday(List<Case> cases) {
        List<Case> chosen = new ArrayList<>();
        for (Case c : cases) {
            if (c.plannedForToday()) chosen.add(c);
        }
        return chosen;
    }
}