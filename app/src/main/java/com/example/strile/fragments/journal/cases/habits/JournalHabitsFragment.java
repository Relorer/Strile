package com.example.strile.fragments.journal.cases.habits;

import com.example.strile.activities.habit.HabitActivity;
import com.example.strile.activities.habit.HabitPresenter;
import com.example.strile.database.entities.Case;
import com.example.strile.database.entities.Habit;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalHabitsFragment extends JournalCasesFragment {
    @Override
    protected void startCaseActivity(Case c) {
        HabitActivity.start(this, (Habit)c);
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalHabitsPresenter();
    }
}
