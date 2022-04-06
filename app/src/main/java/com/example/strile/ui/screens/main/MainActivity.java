package com.example.strile.ui.screens.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.ui.screens.main.journal.JournalFragment;
import com.example.strile.ui.screens.main.progress.ProgressFragment;
import com.example.strile.ui.screens.main.timer.TimerFragment;
import com.example.strile.infrastructure.presenter.PresenterManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private final JournalFragment journalFragment = new JournalFragment();
    private final TimerFragment timerFragment = new TimerFragment();
    private final ProgressFragment progressFragment = new ProgressFragment();
    private MainPresenter presenter;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new MainPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_main);

        toConfigureBottomNavigation();
    }

    public void setVisibleFragment(int itemId) {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().hide(journalFragment).hide(timerFragment).hide(progressFragment).commit();
        switch (itemId) {
            case R.id.item_journal:
                fragmentManager.beginTransaction().show(journalFragment).commit();
                break;
            case R.id.item_timer:
                fragmentManager.beginTransaction().show(timerFragment).commit();
                break;
            case R.id.item_progress:
                fragmentManager.beginTransaction().show(progressFragment).commit();
                break;
        }
    }

    private void toConfigureBottomNavigation() {
        fragmentManager = getSupportFragmentManager();
        for (Fragment fragment : fragmentManager.getFragments()) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        fragmentManager.beginTransaction()
                .add(R.id.frame_main, journalFragment, "journal")
                .add(R.id.frame_main, timerFragment, "timer")
                .add(R.id.frame_main, progressFragment, "progress")
                .commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            presenter.bottomNavigationSelectedItemChanged(menuItem.getItemId());
            setVisibleFragment(menuItem.getItemId());
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}