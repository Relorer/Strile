package com.example.strile.activities.case_activity.habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.sevice.presenter.PresenterManager;

public class HabitActivity extends BaseCaseActivity {

HabitModel habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habit = getIntent().getParcelableExtra(HabitModel.class.getCanonicalName());
        super.onCreate(savedInstanceState);
        textTitle.setText("Habit");
        imageSpecialPurpose.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurpose.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
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

}
