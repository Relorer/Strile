package com.example.strile.database.entities;

import android.os.Parcelable;

import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.event_handler_interfaces.OnChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

public abstract class CaseModel extends BaseModel implements Parcelable {

    protected String name = "";
    protected int difficulty;

    public CaseModel() {
        super();
    }

    public abstract void setState(boolean complete);
    public abstract boolean plannedForDay();
    public abstract boolean isCompleted();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void visibleDayChanged() {
        notifyOfChanges();
    }
}
