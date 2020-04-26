package com.example.strile.fragments.journal.cases.habits;

import com.example.strile.activities.case_activity.habit.HabitActivity;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.JournalCasesPresenter;

public class JournalHabitsFragment extends JournalCasesFragment implements JournalCasesPage {
    @Override
    protected void startCaseActivity(CaseModel c) {
        HabitActivity.start(this, (HabitModel)c);
    }

    @Override
    protected JournalCasesPresenter getPresenter() {
        return new JournalHabitsPresenter();
    }

    @Override
    public String getTitle() {
        return "   Habits   ";
    }
}
