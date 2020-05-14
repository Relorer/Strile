package com.example.strile.sevice;

public interface TimerView {
    void setTextInfo(String text);

    void setTotalTimeOnCanvas(long time);

    void setCurrentTimeOnCanvas(long time);

    void setTextTime(long time);

    void setTextButtonTimerControlSecondary(String text);

    void setTextButtonTimerControlPrimary(String text);

    void setTextItemTitle(String text);
}
