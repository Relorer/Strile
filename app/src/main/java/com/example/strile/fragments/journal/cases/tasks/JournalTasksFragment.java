package com.example.strile.fragments.journal.cases.tasks;

import com.example.strile.activities.task.TaskActivity;
import com.example.strile.database.entities.Case;
import com.example.strile.database.entities.Task;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;


public class JournalTasksFragment extends JournalCasesFragment {

    @Override
    protected void startCaseActivity(Case c) {
        TaskActivity.start(this, (Task) c);
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalTasksPresenter();
    }
}
