package com.example.strile.activities.case_activity.add_case.add_habit;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;

public class AddHabitActivity extends BaseCaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textTitle.setText("Create Habit");
        imageSpecialPurpose.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.tick, null));
        imageSpecialPurpose.setColorFilter(getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }

    public static void start(Fragment caller) {
        setCaller(caller);
        Intent intent = new Intent(caller.getContext(), AddHabitActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new AddHabitPresenter();
    }
}