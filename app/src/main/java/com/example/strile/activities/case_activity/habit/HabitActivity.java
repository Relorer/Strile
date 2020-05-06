package com.example.strile.activities.case_activity.habit;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.activities.timer.TimerActivity;
import com.example.strile.database.entities.Habit;

public class HabitActivity extends BaseCaseActivity {

Habit habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habit = getIntent().getParcelableExtra(Habit.class.getCanonicalName());
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.t_habit);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        //todo
        return new HabitPresenter();
    }

    public static void start(Fragment caller, Habit habit) {
        setCaller(caller);
        Intent intent = new Intent(caller.getContext(), HabitActivity.class);
        intent.putExtra(Habit.class.getCanonicalName(), habit);
        caller.startActivity(intent);
    }

    public void openTimer(Habit habit) {
        TimerActivity.startForResult(this, habit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Habit habit = data.getParcelableExtra(Habit.class.getCanonicalName());
//        ((HabitPresenter)getPresenter()).setHabit(habit);
    }
}
