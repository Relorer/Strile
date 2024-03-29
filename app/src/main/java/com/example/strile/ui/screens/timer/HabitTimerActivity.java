package com.example.strile.ui.screens.timer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.infrastructure.presenter.PresenterManager;
import com.example.strile.infrastructure.timer.TimerView;
import com.example.strile.ui.views.TimerCanvas;

public class HabitTimerActivity extends AppCompatActivity implements TimerView {

    private static final String HABIT_ID = "habit_id";

    private HabitTimerPresenter presenter;

    private TextView textInfo;
    private TextView textTime;
    private TextView textItemTitle;
    private TimerCanvas timerCanvas;
    private Button buttonTimerControlPrimary;
    private TextView buttonTimerControlSecondary;

    public static void start(Activity caller, String habitId) {
        Intent intent = new Intent(caller, HabitTimerActivity.class);
        intent.putExtra(HABIT_ID, habitId);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String habitId = getIntent().getStringExtra(HABIT_ID);
        if (habitId == null)
            throw new IllegalArgumentException("Habit activity received an incorrect id");
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new HabitTimerPresenter(habitId);
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.timer_view);
        final ImageView imageSpecialPurposeLeft = findViewById(R.id.image_special_purpose_button_left);
        final View buttonSpecialPurposeLeft = findViewById(R.id.special_purpose_button_left);

        TextView textTitle = findViewById(R.id.text_title);
        textTime = findViewById(R.id.text_timer_time);
        textItemTitle = findViewById(R.id.text_item_title);
        timerCanvas = findViewById(R.id.view_canvas);
        textInfo = findViewById(R.id.text_info);
        buttonTimerControlPrimary = findViewById(R.id.button_timer_control_primary);
        buttonTimerControlSecondary = findViewById(R.id.button_skip_auth);

        textTitle.setText(R.string.focus_timer);

        buttonTimerControlPrimary.setOnClickListener(v -> presenter.buttonTimerControlPrimaryClicked());
        buttonTimerControlSecondary.setOnClickListener(v -> presenter.buttonTimerControlSecondaryClicked());

        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back_arrow, null));
        buttonSpecialPurposeLeft.setOnClickListener(v -> presenter.backButtonClicked());
    }

    @Override
    public void setTextInfo(String text) {
        textInfo.setText(text);
    }

    @Override
    public void setTotalTimeOnCanvas(long time) {
        timerCanvas.setTotalTime(time);
    }

    @Override
    public void setCurrentTimeOnCanvas(long time) {
        timerCanvas.setCurrentTime(time);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void setTextTime(long time) {
        final long minutes = time / 60000;
        final long seconds = time / 1000 % 60;
        textTime.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    public void setTextButtonTimerControlSecondary(String text) {
        buttonTimerControlSecondary.setText(text);
    }

    @Override
    public void setTextButtonTimerControlPrimary(String text) {
        buttonTimerControlPrimary.setText(text);
    }

    @Override
    public void setTextItemTitle(String text) {
        textItemTitle.setText(text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}