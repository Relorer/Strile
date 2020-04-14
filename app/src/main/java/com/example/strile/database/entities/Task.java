package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.strile.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("ParcelCreator")
@Entity(tableName = "task")
public class Task implements Case {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name = "";
    private String description = "";
    private int difficulty;
    private long deadline;

    public Task() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public void setState(boolean complete) {

    }

    @Override
    public boolean isGoalTime() {
        return false;
    }

    @Override
    public boolean plannedForToday() {
        return true;
    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public boolean infoImportant() {
        return new Date().getTime() >= deadline;
    }

    @Override
    public String getInfo() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(deadline);
        return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeadline() {
        return deadline != 0;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
