package com.example.strile.fragments.journal.cases.habits;

import com.example.strile.activities.case_activity.habit.HabitActivity;
import com.example.strile.database.entities.Habit;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalHabitsFragment extends JournalCasesFragment implements JournalCasesPage {

    public void startHabitActivity(Habit habit) {
        HabitActivity.start(this.getActivity(), habit);
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalHabitsPresenter();
    }

    @Override
    public String getTitle() {
        return String.format("   %s   ", "Habits");
    }
}
