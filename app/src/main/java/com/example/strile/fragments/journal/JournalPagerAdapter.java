package com.example.strile.fragments.journal;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.strile.fragments.journal.cases.habits.JournalHabitsFragment;
import com.example.strile.fragments.journal.cases.tasks.JournalTasksFragment;

public class JournalPagerAdapter extends FragmentPagerAdapter {
    public JournalPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return position == 0 ? new JournalHabitsFragment() : new JournalTasksFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {
        return position == 0 ? "   Habits   " : "   Tasks   ";
    }
}
