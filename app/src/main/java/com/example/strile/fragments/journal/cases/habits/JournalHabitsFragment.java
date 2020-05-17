package com.example.strile.fragments.journal.cases.habits;

import com.example.strile.R;
import com.example.strile.activities.case_activity.habit.HabitActivity;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalHabitsFragment extends JournalCasesFragment implements JournalCasesPage {

    public void startHabitActivity(long habitId) {
        HabitActivity.start(this.getActivity(), habitId);
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
