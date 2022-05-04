package com.example.strile.ui.screens.case_activity.task;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.ui.screens.case_activity.BaseCaseActivity;
import com.example.strile.ui.screens.case_activity.BaseCasePresenter;
import com.example.strile.utilities.callbacks.ShowSnackbarCallback;

public class TaskActivity extends BaseCaseActivity {

    private static final String TASK_ID = "task_id";

    private static ShowSnackbarCallback lastCallback;

    private String taskId;

    public static void start(Activity caller, String taskId, ShowSnackbarCallback callback) {
        lastCallback = callback;
        Intent intent = new Intent(caller, TaskActivity.class);
        intent.putExtra(TASK_ID, taskId);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        taskId = getIntent().getStringExtra(TASK_ID);
        if (taskId == null)
            throw new IllegalArgumentException("Task activity received an incorrect id");
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.task);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new TaskPresenter(taskId);
    }

    public void showSnackbar(String text, String actionName, View.OnClickListener onClickListener) {
        if (lastCallback != null)
            lastCallback.show(text, actionName, onClickListener);
    }

}