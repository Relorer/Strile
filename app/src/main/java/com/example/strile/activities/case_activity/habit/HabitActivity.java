package com.example.strile.activities.case_activity.habit;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.activities.timer.TimerActivity;
import com.example.strile.database.entities.HabitModel;

public class HabitActivity extends BaseCaseActivity {

HabitModel habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habit = getIntent().getParcelableExtra(HabitModel.class.getCanonicalName());
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.t_habit);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new HabitPresenter(habit);
    }

    public static void start(Fragment caller, HabitModel habitModel) {
        setCaller(caller);
        Intent intent = new Intent(caller.getContext(), HabitActivity.class);
        intent.putExtra(HabitModel.class.getCanonicalName(), habitModel);
        caller.startActivity(intent);
    }

    public void openTimer(HabitModel habit) {
        TimerActivity.startForResult(this, habit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        HabitModel habit = data.getParcelableExtra(HabitModel.class.getCanonicalName());
        ((HabitPresenter)getPresenter()).setHabit(habit);
    }
}
