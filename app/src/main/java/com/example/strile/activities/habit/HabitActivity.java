package com.example.strile.activities.habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.strile.R;
import com.example.strile.activities.add_habit.AddHabitActivity;
import com.example.strile.activities.add_habit.AddHabitPresenter;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.presenter.PresenterManager;

public class HabitActivity extends AppCompatActivity {

    private HabitPresenter presenter;

    private TextView textName;
    private View buttonBack;
    private View buttonToolbar;
    private View viewTimeGoal;
    private TextView textTimeProgress;
    private ProgressBar progressBarTime;
    private TextView textStreak;
    private TextView goalTimeText;
    private TextView repeatText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Habit habit = getIntent().getParcelableExtra(
                Habit.class.getCanonicalName());

        if (savedInstanceState == null) {
            presenter = new HabitPresenter(habit);
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_habit);

        textName = findViewById(R.id.text_name);
        buttonBack = findViewById(R.id.view_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backButtonClicked();
            }
        });
        buttonToolbar = findViewById(R.id.view_toolBar);
        viewTimeGoal = findViewById(R.id.view_timeGoal);
        textTimeProgress = findViewById(R.id.text_time_progress);
        progressBarTime = findViewById(R.id.progressBar_time_progress);
        textStreak = findViewById(R.id.text_streak);
        repeatText = findViewById(R.id.text_repeat);
        goalTimeText = findViewById(R.id.text_goal);
    }

    public static void start(Fragment caller, Habit habit) {
        Intent intent = new Intent(caller.getContext(), HabitActivity.class);
        intent.putExtra(Habit.class.getCanonicalName(), habit);
        caller.startActivity(intent);
    }

    void setTextName(String name) {
        textName.setText(name);
    }

    void setVisibleViewTimeGoal(boolean visible) {
        if (visible) viewTimeGoal.setVisibility(View.VISIBLE);
        else viewTimeGoal.setVisibility(View.GONE);
    }

    void setTextTimeProgress(String text) {
        textTimeProgress.setText(text);
    }

    void setTextTimeProgressColor(int color) {
        textTimeProgress.setTextColor(getColor(color));
    }

    void setProgressBarTimeMaximum(int maximum) {
        progressBarTime.setMax(maximum);
    }

    void setProgressBarTimeCurrentValue(int num) {
        progressBarTime.setProgress(num);
    }

    void setProgressBarTimeColor(int color) {
        progressBarTime.setProgressTintList(getColorStateList(color));
    }

    void setTextStreak(String text) {
        textStreak.setText(text);
    }

    void setTextStreakColor(int color) {
        textStreak.setTextColor(getColor(color));
    }

    void setGoalTimeText(String text) {
        goalTimeText.setText(text);
    }

    void setRepeatText(String text) {
        repeatText.setText(text);
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
