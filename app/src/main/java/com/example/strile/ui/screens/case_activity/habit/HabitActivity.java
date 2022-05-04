package com.example.strile.ui.screens.case_activity.habit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.BaseCaseActivity;
import com.example.strile.ui.screens.case_activity.BaseCasePresenter;
import com.example.strile.ui.screens.timer.HabitTimerActivity;
import com.example.strile.utilities.callbacks.ShowSnackbarCallback;

public class HabitActivity extends BaseCaseActivity {

    private static final String HABIT_ID = "habit_id";

    private static ShowSnackbarCallback lastCallback;

    private String habitId;

    public static void start(Activity caller, String habitId, ShowSnackbarCallback callback) {
        lastCallback = callback;
        Intent intent = new Intent(caller, HabitActivity.class);
        intent.putExtra(HABIT_ID, habitId);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habitId = getIntent().getStringExtra(HABIT_ID);
        if (habitId == null)
            throw new IllegalArgumentException("Habit activity received an incorrect id");
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

    public void openTimer(String habitId) {
        HabitTimerActivity.start(this, habitId);
    }
}
