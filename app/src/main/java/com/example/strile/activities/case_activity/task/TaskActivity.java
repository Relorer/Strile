package com.example.strile.activities.case_activity.task;

import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Task;

public class TaskActivity extends BaseCaseActivity {

    private static final String TASK_ID = "task_id";

    private long taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        taskId = getIntent().getLongExtra(TASK_ID, -1);
        if (taskId == -1) throw new IllegalArgumentException("Task activity received an incorrect id");
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.task);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new TaskPresenter(taskId);
    }

    public static void start(Activity caller, long taskId) {
        setCaller(caller);
        Intent intent = new Intent(caller, TaskActivity.class);
        intent.putExtra(TASK_ID, taskId);
        caller.startActivity(intent);
    }

}