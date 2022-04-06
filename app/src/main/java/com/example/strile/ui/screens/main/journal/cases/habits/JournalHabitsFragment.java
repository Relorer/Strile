package com.example.strile.ui.screens.main.journal.cases.habits;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.habit.HabitActivity;
import com.example.strile.ui.screens.main.journal.JournalFragment;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesFragment;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPage;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPresenter;

public class JournalHabitsFragment extends JournalCasesFragment implements JournalCasesPage {

    public void startHabitActivity(long habitId) {
        HabitActivity.start(this.getActivity(), habitId, (text, actionName, callback) -> {
            if (getParentFragment() != null)
                ((JournalFragment) getParentFragment()).showSnackbar(text, actionName, callback);
        });
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalHabitsPresenter();
    }

    @Override
    public int getTitleStringId() {
        return R.string.habits;
    }
}
