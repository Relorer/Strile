package com.example.strile.activities.case_activity.task;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.TaskModel;

public class TaskActivity extends BaseCaseActivity {

    private TaskModel task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        task = getIntent().getParcelableExtra(TaskModel.class.getCanonicalName());
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.task);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        return new TaskPresenter(task);
    }

    public static void start(Fragment caller, TaskModel taskModel) {
        setCaller(caller);
        Intent intent = new Intent(caller.getContext(), TaskActivity.class);
        intent.putExtra(TaskModel.class.getCanonicalName(), taskModel);
        caller.startActivity(intent);
    }

}
