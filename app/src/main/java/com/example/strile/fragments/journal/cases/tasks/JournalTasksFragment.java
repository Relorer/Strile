package com.example.strile.fragments.journal.cases.tasks;

import com.example.strile.R;
import com.example.strile.activities.case_activity.task.TaskActivity;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalTasksFragment extends JournalCasesFragment implements JournalCasesPage {

    public void startTaskActivity(long TaskId) {
        TaskActivity.start(this.getActivity(), TaskId);
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalTasksPresenter();
    }

    @Override
    public int getTitleStringId() {
        return R.string.tasks;
    }
}
