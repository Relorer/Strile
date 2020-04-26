package com.example.strile.fragments.journal.cases.tasks;

import com.example.strile.App;
import com.example.strile.database.models.CaseDatabaseModel;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalTasksPresenter extends JournalCasesPresenter {
    @Override
    protected CaseDatabaseModel getModel() {
        return App.getInstance().getTaskModel();
    }
}
