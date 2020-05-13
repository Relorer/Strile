package com.example.strile.activities.case_activity.add_case.add_task;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;

public class AddTaskActivity extends BaseCaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.create_task);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.tick, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }

    public static void start(Activity caller) {
        setCaller(caller);
        Intent intent = new Intent(caller, AddTaskActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new AddTaskPresenter();
    }
}