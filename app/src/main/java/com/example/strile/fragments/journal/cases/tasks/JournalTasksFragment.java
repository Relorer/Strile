package com.example.strile.fragments.journal.cases.tasks;

import com.example.strile.activities.case_activity.task.TaskActivity;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.database.entities.TaskModel;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;


public class JournalTasksFragment extends JournalCasesFragment implements JournalCasesPage {

    @Override
    protected void startCaseActivity(CaseModel c) {
        TaskActivity.start(this, (TaskModel) c);
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalTasksPresenter();
    }

    @Override
    public String getTitle() {
        return "   Tasks   ";
    }
}
