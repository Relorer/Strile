package com.example.strile.service.structures;

public class DateCompleted {
    private final long date;
    private boolean complete;

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

    public void setState(boolean state) {
        complete = state;
    }
}