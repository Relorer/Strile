package com.example.strile.sevice.structures;

public class DateCompleted{
    private final long date;
    private final boolean complete;

    public DateCompleted(long date, boolean complete) {
        this.date = date;
        this.complete = complete;
    }

    public long getDate() {
        return date;
    }

    public boolean isComplete() {
        return complete;
    }

    public DateCompleted setState(boolean state) {
        return new DateCompleted(date, state);
    }
}