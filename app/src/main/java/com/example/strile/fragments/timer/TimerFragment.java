package com.example.strile.fragments.timer;

import android.annotation.SuppressLint;
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

import com.example.strile.sevice.timer.TimerView;
import com.example.strile.sevice.presenter.PresenterManager;
import com.example.strile.views.TimerCanvas;

public class TimerFragment extends Fragment implements TimerView {

    private TimerPresenter presenter;

    private TextView textTime;
    private Button buttonTimerControlPrimary;
    private TextView textItemTitle;
    private TextView textInfo;
    private TextView textButtonTimerControlSecondary;
    private TimerCanvas timerCanvas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_view, container, false);

        TextView textTitle = view.findViewById(R.id.text_title);
        textTime = view.findViewById(R.id.text_timer_time);
        textItemTitle = view.findViewById(R.id.text_item_title);
        timerCanvas = view.findViewById(R.id.view_canvas);
        textInfo = view.findViewById(R.id.text_info);
        buttonTimerControlPrimary = view.findViewById(R.id.button_timer_control_primary);
        textButtonTimerControlSecondary = view.findViewById(R.id.button_timer_control_secondary);

        textTitle.setText(R.string.focus_timer);

        buttonTimerControlPrimary.setOnClickListener(v -> {
            presenter.buttonTimerControlPrimaryClicked();
        });
        textButtonTimerControlSecondary.setOnClickListener(v -> {
            presenter.buttonTimerControlSecondaryClicked();
        });

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

    @SuppressLint("DefaultLocale")
    @Override
    public void setTextTime(long time) {
        final long minutes = time / 60000;
        final long seconds = time / 1000 % 60;
        textTime.setText(String.format("%02d:%02d", minutes, seconds));
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState == null) {
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