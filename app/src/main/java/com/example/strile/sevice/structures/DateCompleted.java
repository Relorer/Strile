package com.example.strile.sevice.structures;

import android.util.Log;

import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public class DateCompleted {
    private long date;
    private boolean complete;

    public DateCompleted(long date, boolean complete) {
        this.date = date;
        this.complete = complete;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
