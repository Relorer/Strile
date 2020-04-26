package com.example.strile.fragments.journal;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.habits.JournalHabitsFragment;
import com.example.strile.fragments.journal.cases.tasks.JournalTasksFragment;

import java.util.ArrayList;
import java.util.List;

public class JournalPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public JournalPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment instanceof JournalCasesPage)
            return ((JournalCasesPage) fragment).getTitle();
        return "";
    }

}
