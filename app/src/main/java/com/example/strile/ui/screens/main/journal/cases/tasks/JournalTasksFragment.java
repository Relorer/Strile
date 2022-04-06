package com.example.strile.ui.screens.main.journal.cases.tasks;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.task.TaskActivity;
import com.example.strile.ui.screens.main.journal.JournalFragment;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesFragment;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPage;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPresenter;

public class JournalTasksFragment extends JournalCasesFragment implements JournalCasesPage {

    public void startTaskActivity(long TaskId) {
        TaskActivity.start(this.getActivity(), TaskId, (text, actionName, callback) -> {
            if (getParentFragment() != null)
                ((JournalFragment) getParentFragment()).showSnackbar(text, actionName, callback);
        });
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
