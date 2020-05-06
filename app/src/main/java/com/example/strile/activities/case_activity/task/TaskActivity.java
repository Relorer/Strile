package com.example.strile.activities.case_activity.task;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.case_activity.BaseCaseActivity;
import com.example.strile.activities.case_activity.BaseCasePresenter;
import com.example.strile.database.entities.Task;

public class TaskActivity extends BaseCaseActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        task = getIntent().getParcelableExtra(Task.class.getCanonicalName());
        super.onCreate(savedInstanceState);
        textTitle.setText(R.string.task);
        imageSpecialPurposeRight.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.basket, null));
        imageSpecialPurposeRight.setColorFilter(getColor(R.color.colorGray), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    protected BaseCasePresenter getNewPresenter() {
        //todo
        return new TaskPresenter();
    }

    public static void start(Fragment caller, Task task) {
        setCaller(caller);
        Intent intent = new Intent(caller.getContext(), TaskActivity.class);
        intent.putExtra(Task.class.getCanonicalName(), task);
        caller.startActivity(intent);
    }

}
