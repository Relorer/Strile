package com.example.strile.ui.screens.main.journal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.strile.App;
import com.example.strile.ui.screens.main.journal.cases.JournalCasesPage;

import java.util.List;

class JournalPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    public JournalPagerAdapter(FragmentManager fm, @NonNull List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
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
        if (fragment instanceof JournalCasesPage) {
            String title = App.getInstance().getString(((JournalCasesPage) fragment).getTitleStringId());
            return String.format("   %s   ", title);
        }
        return "";
    }
}