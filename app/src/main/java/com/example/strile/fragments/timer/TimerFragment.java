package com.example.strile.fragments.timer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.strile.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.strile.database.entities.Habit;
import com.example.strile.views.TimerCanvas;
import com.example.strile.sevice.presenter.PresenterManager;

public class TimerFragment extends Fragment {

    private TimerPresenter presenter;

    private View view;

    private TextView textTitle;
    private TextView textTime;
    private Button buttonTimerControlPrimary;
    private TextView textItemTitle;
    private TextView textInfo;
    private TextView textButtonTimerControlSecondary;
    private TimerCanvas timerCanvas;

    private Habit habit;

    public TimerFragment() {
    }

    public TimerFragment(Habit habit) {
        this.habit = habit;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        textTitle = view.findViewById(R.id.text_title);
        textTime = view.findViewById(R.id.text_timer_time);
        buttonTimerControlPrimary = view.findViewById(R.id.button_timer_control_primary);
        textItemTitle = view.findViewById(R.id.text_item_title);
        timerCanvas = view.findViewById(R.id.view_canvas);
        textInfo = view.findViewById(R.id.text_info);
        textButtonTimerControlSecondary = view.findViewById(R.id.text_timer_control_secondary);

        textTitle.setText(R.string.focus_timer);
//todo
        buttonTimerControlPrimary.setOnClickListener(v -> {
//            presenter.buttonTimerControlClicked(buttonTimerControlPrimary.getText().toString());
        });
        textButtonTimerControlSecondary.setOnClickListener(v -> {
//            presenter.textButtonTimerControlClicked(textButtonTimerControlSecondary.getText().toString());
        });

        this.view = view;
        return view;
    }

    public void setTextInfo(String text) {
        this.textInfo.setText(text);
    }

    public void setTotalTimeOnCanvas(long time) {
        timerCanvas.setTotalTime(time);
    }

    public void setCurrentTimeOnCanvas(long time) {
        timerCanvas.setCurrentTime(time);
    }

    public void setTextTime(String text) {
        textTime.setText(text);
    }

    public void setTextButtonTimerControlSecondary(String text) {
        textButtonTimerControlSecondary.setText(text);
    }

    public void setTextButtonTimerControlPrimary(String text) {
        buttonTimerControlPrimary.setText(text);
    }

    public void setTextItemTitle(String text) {
        textItemTitle.setText(text);
    }

    public Habit getHabit() {
        return habit;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            //todo
            presenter = new TimerPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

}