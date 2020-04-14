package com.example.strile.database.entities;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public interface Case extends Parcelable {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    int getDifficulty();
    void setDifficulty(int difficulty);
    void setState(boolean complete);
    boolean isGoalTime();
    boolean plannedForToday();
    boolean completed();
    boolean infoImportant();
    String getInfo();
}
