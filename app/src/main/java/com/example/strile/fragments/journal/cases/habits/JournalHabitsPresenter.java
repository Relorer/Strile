package com.example.strile.fragments.journal.cases.habits;

import com.example.strile.App;
import com.example.strile.database.models.CaseModel;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalHabitsPresenter extends JournalCasesPresenter {
    @Override
    protected CaseModel getModel() {
        return App.getInstance().getHabitModel();
    }
}
