package com.example.strile.activities.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.strile.R;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.database.entities.TaskModel;
import com.example.strile.fragments.timer.TimerFragment;

public class TimerActivity extends AppCompatActivity {
    private HabitModel habit;

    private TimerFragment timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habit = getIntent().getParcelableExtra(HabitModel.class.getCanonicalName());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timer);
        timer = new TimerFragment(habit);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, timer, "timer")
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView imageSpecialPurposeLeft = timer.getView().findViewById(R.id.image_special_purpose_button_left);
        View buttonSpecialPurposeLeft = timer.getActivity().findViewById(R.id.special_purpose_button_left);

        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back_arrow, null));
        buttonSpecialPurposeLeft.setOnClickListener(v -> {
            finish();
        });

        Intent intent = new Intent();
        intent.putExtra(HabitModel.class.getCanonicalName(), timer.getHabit());
        setResult(RESULT_OK, intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static void startForResult(Activity caller, HabitModel habitModel) {
        Intent intent = new Intent(caller, TimerActivity.class);
        intent.putExtra(HabitModel.class.getCanonicalName(), habitModel);
        caller.startActivityForResult(intent, 12);
    }
}