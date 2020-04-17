package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.strile.R;
import com.example.strile.database.entities.Case;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.converters.DatesConverter;
import com.example.strile.sevice.recycler_view_adapter.ItemModel;
import com.example.strile.sevice.structures.DateCompleted;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuppressLint("ParcelCreator")
@Entity(tableName = "habit")
public class Habit extends Case {

    private String name = "";
    private int difficulty;
    private int goalTimeSeconds;
    private int elapsedTimeSeconds;
    private int streak;
    private int daysRepeat;

    @TypeConverters({DatesConverter.class})
    private List<DateCompleted> datesCompleted = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habit habit = (Habit) o;
        return difficulty == habit.difficulty &&
                goalTimeSeconds == habit.goalTimeSeconds &&
                elapsedTimeSeconds == habit.elapsedTimeSeconds &&
                streak == habit.streak &&
                daysRepeat == habit.daysRepeat &&
                name.equals(habit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, difficulty, goalTimeSeconds, elapsedTimeSeconds, streak, daysRepeat, datesCompleted);
    }

    @Override
    public void setState(boolean complete) {
        boolean current = datesCompleted.get(datesCompleted.size() - 1).complete;
        if (complete != current) {
            if (complete) streak++;
            else streak--;
        }
        datesCompleted.get(datesCompleted.size() - 1).complete = complete;
    }

    @Override
    public boolean plannedForToday() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if ((daysRepeat & (int) Math.pow(2, day)) == Math.pow(2, day)) {
            long today = DateManager.getDateWithoutTimeUsingCalendar().getTime();

            if (datesCompleted.size() == 0) {
                datesCompleted.add(new DateCompleted(today, false));
            }

            DateCompleted date = datesCompleted.get(datesCompleted.size() - 1);

            if (date.date != today) {
                if (!date.complete) streak = 0;
                datesCompleted.add(new DateCompleted(today, false));
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean completed() {
        return datesCompleted.get(datesCompleted.size() - 1).complete;
    }

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

    public List<DateCompleted> getDatesCompleted() {
        return datesCompleted;
    }

    public void setDatesCompleted(List<DateCompleted> datesCompleted) {
        this.datesCompleted = datesCompleted;
    }

    public int getDaysRepeat() {
        return daysRepeat;
    }

    public boolean[] getDaysRepeatAsArray() {
        boolean[] days = new boolean[7];
        for (int i = 0; i < days.length; i++) {
            days[i] = (daysRepeat & (int) Math.pow(2, i)) == Math.pow(2, i);
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
        dest.writeString(new DatesConverter().fromDatesList(datesCompleted));
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
            habit.setDatesCompleted(new DatesConverter().toDatesList(source.readString()));
            return habit;
        }

        @Override
        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };

    @Override
    public int getType() {
        return ItemModel.HABIT_TYPE;
    }
}