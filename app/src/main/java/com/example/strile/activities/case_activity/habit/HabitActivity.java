package com.example.strile.activities.case_activity.habit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.activities.timer.HabitTimerActivity;
import com.example.strile.sevice.call_back_interfaces.ShowSnackbarCallback;

public class HabitActivity extends BaseCaseActivity {

    private static final String HABIT_ID = "habit_id";

    private static ShowSnackbarCallback lastCallback;

    private long habitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habitId = getIntent().getLongExtra(HABIT_ID, -1);
        if (habitId == -1) throw new IllegalArgumentException("Habit activity received an incorrect id");
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.t_habit);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new HabitPresenter(habitId);
    }

    public void showSnackbar(String text, String actionName, View.OnClickListener onClickListener) {
        if (lastCallback != null)
            lastCallback.show(text, actionName, onClickListener);
    }

    public static void start(Activity caller, long habitId, ShowSnackbarCallback callback) {
        lastCallback = callback;
        Intent intent = new Intent(caller, HabitActivity.class);
        intent.putExtra(HABIT_ID, habitId);
        caller.startActivity(intent);
    }

    public void openTimer(long habitId) {
        HabitTimerActivity.start(this, habitId);
    }
}
