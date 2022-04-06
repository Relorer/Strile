package com.example.strile.infrastructure.timer;

public interface TimerState {

    String TIMER_ID = "focus_timer_id";

    void primaryButtonClicked();

    void secondaryButtonClicked();
}
