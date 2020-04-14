package com.example.strile.fragments.journal.cases;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.strile.database.entities.Case;
import com.example.strile.database.models.CaseModel;
import com.example.strile.sevice.call_back_interfaces.CompleteLoadCallback;
import com.example.strile.sevice.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public abstract class JournalCasesPresenter extends BasePresenter<CaseModel, JournalCasesFragment> {

    private LiveData<List<Case>> cases;
    private List<Case> incomplete;
    private List<Case> completed;

    private boolean buttonHidingState = true;

    public JournalCasesPresenter() {
        model = getModel();
        model.loadCases(new CompleteLoadCallback() {
            @Override
            public void onComplete(LiveData<?> list) {
                cases = (LiveData<List<Case>>) list;;
                updateView();
            }
        });
    }

    protected abstract CaseModel getModel();

    @Override
    protected void updateView() {
        if (cases != null) {
            cases.observe(view(), new Observer<List<Case>>() {
                @Override
                public void onChanged(List<Case> cases) {
                    List<Case> forToday = chooseForToday(cases);
                    incomplete = chooseIncomplete(forToday);
                    completed = chooseCompleted(forToday);

                    view().setSortedListIncomplete(incomplete);

                    if (buttonHidingState) view().setSortedListCompleted(null);
                    else view().setSortedListCompleted(completed);
                    if (completed.size() > 0) view().setVisibleButtonHiding(true);
                    view().updateButtonHidingCount(completed.size());
                }
            });
        }
        view().setCheckedButtonHiding(buttonHidingState);
    }

    void itemClicked(Case c) {
        view().startCaseActivity(c);
    }

    void itemStateChanged(Case c) {
        updateCase(c);
    }

    void buttonHidingStateChanged() {
        buttonHidingState = !buttonHidingState;
        view().setCheckedButtonHiding(buttonHidingState);
        if (buttonHidingState) view().setSortedListCompleted(null);
        else view().setSortedListCompleted(completed);
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