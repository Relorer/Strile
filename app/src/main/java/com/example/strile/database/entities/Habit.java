package com.example.strile.database.entities;


import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.strile.R;
import com.example.strile.database.entities.Case;
import com.example.strile.sevice.DateManager;

import java.security.cert.Certificate;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("ParcelCreator")
@Entity(tableName = "habit")
public class Habit implements Case {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name = "";
    private int difficulty;
    private int goalTimeSeconds;
    private int elapsedTimeSeconds;
    private int streak;
    private int daysRepeat;
    private long dateLastExecution;

    public Habit() {}

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
        if (complete) dateLastExecution = DateManager.getDateWithoutTimeUsingCalendar().getTime();
        else dateLastExecution = 0;
    }

    @Override
    public boolean isGoalTime() {
        return goalTimeSeconds > 0;
    }

    @Override
    public boolean plannedForToday() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if ((daysRepeat & (int)Math.pow(2, day)) == Math.pow(2, day)) return true;
        return false;
    }

    @Override
    public boolean completed() {
        return dateLastExecution == DateManager.getDateWithoutTimeUsingCalendar().getTime();
    }

    @Override
    public boolean infoImportant() {
        return false;
    }

    @Override
    public String getInfo() {
        return streak + " day streak";
    }

    public int getGoalTimeSeconds() {
        return goalTimeSeconds;
    }

    public void setGoalTimeSeconds(int goalTimeSeconds) {
        this.goalTimeSeconds = goalTimeSeconds;
    }

    public int getElapsedTimeSeconds() {
        return elapsedTimeSeconds;
    }

    public void setElapsedTimeSeconds(int elapsedTimeSeconds) {
        this.elapsedTimeSeconds = elapsedTimeSeconds;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getDaysRepeat() {
        return daysRepeat;
    }

    public long getDateLastExecution() {
        return dateLastExecution;
    }

    public void setDateLastExecution(long dateLastExecution) {
        this.dateLastExecution = dateLastExecution;
    }

    public boolean[] getDaysRepeatAsArray() {
        boolean[] days = new boolean[7];
        for (int i = 0; i < days.length; i++) {
            days[i] = (daysRepeat & (int)Math.pow(2, i)) == Math.pow(2, i);
        }
        return days;
    }

    public void setDaysRepeat(int daysRepeat) {
        this.daysRepeat = daysRepeat;
    }

    public void setDaysRepeat(boolean[] daysRepeat) {
        this.daysRepeat = 0;
        for (int i = 0; i < daysRepeat.length; i++) {
            if (daysRepeat[i]) this.daysRepeat += Math.pow(2, i);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(difficulty);
        dest.writeInt(goalTimeSeconds);
        dest.writeInt(elapsedTimeSeconds);
        dest.writeInt(streak);
        dest.writeInt(daysRepeat);
        dest.writeLong(dateLastExecution);
    }

    public static final Parcelable.Creator<Habit> CREATOR = new Parcelable.Creator<Habit>() {

        @Override
        public Habit createFromParcel(Parcel source) {
            Habit habit = new Habit();
            habit.setName(source.readString());
            habit.setId(source.readInt());
            habit.setDifficulty(source.readInt());
            habit.setGoalTimeSeconds(source.readInt());
            habit.setElapsedTimeSeconds(source.readInt());
            habit.setStreak(source.readInt());
            habit.setDaysRepeat(source.readInt());
            habit.setDateLastExecution(source.readLong());
            return habit;
        }

        @Override
        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };
}