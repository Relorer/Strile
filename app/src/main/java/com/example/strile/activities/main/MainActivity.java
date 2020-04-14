package com.example.strile.activities.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.strile.R;
import com.example.strile.fragments.journal.JournalFragment;
import com.example.strile.fragments.progress.ProgressFragment;
import com.example.strile.fragments.timer.TimerFragment;
import com.example.strile.sevice.presenter.PresenterManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    private final JournalFragment journalFragment = new JournalFragment();
    private final TimerFragment timerFragment = new TimerFragment();
    private final ProgressFragment progressFragment = new ProgressFragment();

    private FragmentManager fragmentManager;

    private BottomNavigationView bottomNavigationView;

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

    private void toConfigureBottomNavigation() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_main, journalFragment, "journal")
                .add(R.id.frame_main, timerFragment, "timer").hide(timerFragment)
                .add(R.id.frame_main, progressFragment, "progress").hide(progressFragment)
                .commit();


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment active = journalFragment;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                presenter.bottomNavigationSelectedItemChanged(menuItem.getItemId());
                switch (menuItem.getItemId()) {
                    case R.id.item_journal:
                        fragmentManager.beginTransaction().hide(active).show(journalFragment).commit();
                        active = journalFragment;
                        break;
                    case R.id.item_timer:
                        fragmentManager.beginTransaction().hide(active).show(timerFragment).commit();
                        active = timerFragment;
                        break;
                    case R.id.item_progress:
                        fragmentManager.beginTransaction().hide(active).show(progressFragment).commit();
                        active = progressFragment;
                        break;
                }
                return true;
            }
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}