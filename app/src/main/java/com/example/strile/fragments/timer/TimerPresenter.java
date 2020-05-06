package com.example.strile.fragments.timer;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.sevice.presenter.BasePresenter;

public class TimerPresenter extends BasePresenter<TimerFragment> {
    @Override
    protected void updateView() {
        //todo
    }

//    //state timer
//    private final String timerHabit = "timer_habit";
//    private final String timerBreak = "timer_break";
//    private final String timerPomodoro = "timer_pomodoro";
//
//    //primary button
//    private final String start = "Start";
//    private final String resume = "Resume";
//    private final String pause = "Pause";
//
//    //secondary button
//    private final String stop = "Stop";
//    private final String done = "Done";
//    private final String skip = "Skip";
//    private final String letsgo = "Let's go!";
//
//    //pomodoro parameters
//    private final long durationPomodoro = 25 * 60 * 1000;
//    private final long durationBreak = 5 * 60 * 1000;
//    private final long durationLongBreak = 15 * 60 * 1000;
//    private final long frequencyLongBreak = 4;
//    private long currentNumberPomodoro = 1;
//
//    private Habit habit;
//
//    private CountDownTimer timer;
//    private long currentTimeTimer;
//    private String currentState = timerPomodoro;
//
//    private String currentButtonPrimary = start;
//    private String currentButtonSecondary = letsgo;
//
//    public TimerPresenter(Habit habit) {
//        model = App.getInstance().getHabitModel();
//        this.habit = habit;
//        Log.d("MY", "start " + habit + " " + this.habit);
//    }
//
//    @Override
//    protected void updateView() {
//        if (habit != null) {
//            view().setTextItemTitle(habit.getName());
//            view().setTextInfo(habit.getElapsedTimeSeconds() / 60 + " / " + habit.getGoalTimeSeconds() / 60);
//            setCurrentTimeTimer((habit.getGoalTimeSeconds() - habit.getElapsedTimeSeconds()) * 1000);
//            view().setTotalTimeOnCanvas((habit.getGoalTimeSeconds() - habit.getElapsedTimeSeconds()) * 1000);
//        } else {
//            view().setTextItemTitle("Pomodoro");
//            view().setTextInfo("#" + currentNumberPomodoro);
//            setCurrentTimeTimer(durationPomodoro);
//            view().setTotalTimeOnCanvas(durationPomodoro);
//        }
//
//        setCurrentButtonPrimary(currentButtonPrimary);
//        setCurrentButtonSecondary(currentButtonSecondary);
//    }
//
//    public void buttonTimerControlClicked(String state) {
//        if (state.equals(start) || state.equals(resume)) {
//            startTimer();
//            setCurrentButtonPrimary(pause);
//            if (!currentButtonSecondary.equals(skip)) {
//                setCurrentButtonSecondary(stop);
//            }
//        } else if (state.equals(pause)) {
//            pauseTimer();
//        }
//    }
//
//    public void textButtonTimerControlClicked(String text) {
//        switch (text) {
//            case done:
//                if (habit != null) {
//                    habit.setElapsedTimeSeconds((habit.getGoalTimeSeconds()));
//                    view().getActivity().finish();
//                } else donePomodoro();
//                break;
//            case stop:
//                if (habit != null) view().getActivity().finish();
//                else resetTimer();
//                break;
//            case skip:
//                skipBreak();
//                break;
//        }
//    }
//
//    private void donePomodoro() {
//        if (currentNumberPomodoro % frequencyLongBreak == 0) {
//            setCurrentTimeTimer(durationLongBreak);
//            if (view() != null) view().setTextItemTitle(view().getString(R.string.take_a_long_break));
//        } else {
//            setCurrentTimeTimer(durationBreak);
//            if (view() != null) view().setTextItemTitle(view().getString(R.string.take_a_short_break));
//        }
//        if (view() != null) view().setTextInfo("");
//        setCurrentButtonPrimary(start);
//        setCurrentButtonSecondary(skip);
//        if (view() != null) view().setTotalTimeOnCanvas(currentTimeTimer);
//        currentState = timerBreak;
//    }
//
//    private void skipBreak() {
//        timer.cancel();
//        currentNumberPomodoro++;
//        setCurrentTimeTimer(durationPomodoro);
//        if (view() != null) {
//            view().setTotalTimeOnCanvas(durationPomodoro);
//            view().setTextInfo("#" + currentNumberPomodoro);
//            view().setTextItemTitle(view().getString(R.string.pomodoro));
//        }
//        setCurrentButtonSecondary(letsgo);
//        setCurrentButtonPrimary(start);
//        currentState = timerPomodoro;
//    }
//
//    private void startTimer() {
//        timer = new CountDownTimer(currentTimeTimer, 1) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (view() != null) {
//                    view().setTextTime(getStringFormat(millisUntilFinished));
//                    view().setCurrentTimeOnCanvas(millisUntilFinished);
//                    currentTimeTimer = millisUntilFinished;
//                    if (habit != null) {
//                        habit.setElapsedTimeSeconds((habit.getGoalTimeSeconds() - (int) Math.ceil(currentTimeTimer / 1000f / 60f) * 60));
//                        view().setTextInfo(habit.getElapsedTimeSeconds() / 60 + " / " + habit.getGoalTimeSeconds() / 60);
//                    }
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                if (habit == null) {
//                    if (currentState.equals(timerPomodoro)) donePomodoro();
//                    else if (currentState.equals(timerBreak)) skipBreak();
//                }
//                else {
//                    view().getActivity().finish();
//                }
//            }
//        }.start();
//    }
//
//
//    private void pauseTimer() {
//        timer.cancel();
//        setCurrentButtonPrimary(resume);
//        if (!currentButtonSecondary.equals(skip)) {
//            setCurrentButtonSecondary(done);
//        }
//    }
//
//    private void resetTimer() {
//        timer.cancel();
//        setCurrentTimeTimer(durationPomodoro);
//        setCurrentButtonPrimary(start);
//        setCurrentButtonSecondary(letsgo);
//        if (view() != null) view().setTotalTimeOnCanvas(durationPomodoro);
//    }
//
//    private String getStringFormat(long time) {
//        long minutes = (time / 1000) / 60;
//        long seconds = (time / 1000) % 60;
//        return String.format("%02d:%02d", minutes, seconds);
//    }
//
//    private void setCurrentButtonPrimary(String currentButtonPrimary) {
//        this.currentButtonPrimary = currentButtonPrimary;
//        if (view() != null) view().setTextButtonTimerControlPrimary(currentButtonPrimary);
//    }
//
//    private void setCurrentButtonSecondary(String currentButtonSecondary) {
//        this.currentButtonSecondary = currentButtonSecondary;
//        if (view() != null) view().setTextButtonTimerControlSecondary(currentButtonSecondary);
//    }
//
//    private void setCurrentTimeTimer(long currentTimeTimer) {
//        this.currentTimeTimer = currentTimeTimer;
//        if (view() != null) {
//            view().setCurrentTimeOnCanvas(currentTimeTimer);
//            view().setTextTime(getStringFormat(currentTimeTimer));
//        }
//    }
}