package com.example.strile.ui.screens.case_activity.add_case.add_task;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.BaseCaseActivity;
import com.example.strile.ui.screens.case_activity.BaseCasePresenter;

public class AddTaskActivity extends BaseCaseActivity {

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, AddTaskActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.create_task);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.tick, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new AddTaskPresenter();
    }
}