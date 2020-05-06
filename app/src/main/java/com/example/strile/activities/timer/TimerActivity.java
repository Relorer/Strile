package com.example.strile.activities.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.fragments.timer.TimerFragment;

public class TimerActivity extends AppCompatActivity {
    private Habit habit;

    private TimerFragment timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        habit = getIntent().getParcelableExtra(Habit.class.getCanonicalName());
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
        intent.putExtra(Habit.class.getCanonicalName(), timer.getHabit());
        setResult(RESULT_OK, intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static void startForResult(Activity caller, Habit habit) {
        Intent intent = new Intent(caller, TimerActivity.class);
        intent.putExtra(Habit.class.getCanonicalName(), habit);
        caller.startActivityForResult(intent, 12);
    }
}