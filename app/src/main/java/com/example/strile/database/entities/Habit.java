package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.strile.sevice.Day;
import com.example.strile.sevice.converters.DatesConverter;
import com.example.strile.sevice.structures.DateCompleted;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.security.auth.callback.Callback;

@SuppressLint("ParcelCreator")
@Entity(tableName = "habit")
public class Habit implements Parcelable {

    @PrimaryKey
    private final long id;
    private final String name;
    private final int difficulty;
    private final long goalTime;
    private final long elapsedTime;
    private final int daysRepeat;

    @TypeConverters({DatesConverter.class})
    private final List<DateCompleted> datesCompleted;

    public Habit(long id, String name, int difficulty, long goalTime, long elapsedTime,
                 int daysRepeat, @NonNull List<DateCompleted> datesCompleted) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.goalTime = goalTime;
        this.elapsedTime = elapsedTime;
        this.daysRepeat = daysRepeat;
        this.datesCompleted = datesCompleted;
    }

    public Habit(long id, String name, int difficulty, long goalTime, long elapsedTime,
                 boolean[] daysRepeat, @NonNull List<DateCompleted> datesCompleted) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.goalTime = goalTime;
        this.elapsedTime = elapsedTime;
        this.daysRepeat = arrayRepeatToInt(daysRepeat);
        this.datesCompleted = datesCompleted;
    }

    public boolean plannedForDay(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return (daysRepeat & (int) Math.pow(2, weekday)) == Math.pow(2, weekday);
    }

    public boolean isCompleteOnDay(Date date) {
        return getDateCompleted(date).isComplete();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public long getGoalTime() {
        return goalTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public int getDaysRepeat() {
        return daysRepeat;
    }

    public List<DateCompleted> getDatesCompleted() {
        return datesCompleted;
    }

    public int getStreakByDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Day(date).getDateOfDayWithoutTime());
        datesCompleted.sort((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        int streak = 0;
        if (isCompleteOnDay(date)) streak++;
        for (DateCompleted item : datesCompleted) {

            final long itemDate = item.getDate();
            if (itemDate < date.getTime()) {
                while (calendar.getTimeInMillis() != itemDate) {
                    calendar.add(Calendar.DATE, -1);
                    if (calendar.getTimeInMillis() == itemDate) break;
                    if (plannedForDay(calendar.getTimeInMillis())) return streak;
                }
                if (item.isComplete()) streak++;
                else if (plannedForDay(itemDate)) break;
            }

        }
        return streak;
    }

    public boolean[] getDaysRepeatAsArray() {
        boolean[] days = new boolean[7];
        for (int i = 0; i < days.length; i++) {
            days[i] = (daysRepeat & (int) Math.pow(2, i)) == Math.pow(2, i);
        }
        return days;
    }

    public Habit setStateForDay(boolean state, Date date) throws CloneNotSupportedException {
        DateCompleted dateCompleted = getDateCompleted(date);
        List<DateCompleted> dates = new ArrayList<>(datesCompleted);
        dates.remove(dateCompleted);
        dates.add(dateCompleted.setState(state));
        return new Habit(id, name, difficulty, goalTime, elapsedTime, daysRepeat, dates);
    }

    private int arrayRepeatToInt(boolean[] daysRepeat) {
        int repeat = 0;
        for (int i = 0; i < daysRepeat.length; i++) {
            if (daysRepeat[i]) repeat += Math.pow(2, i);
        }
        return repeat;
    }

    private DateCompleted getDateCompleted(Date date) {
        DateCompleted found = datesCompleted.stream().filter(item -> date.getTime() == item.getDate()).findAny().orElse(null);
        if (found == null) {
            DateCompleted newDateCompleted = new DateCompleted(date.getTime(), false);
            datesCompleted.add(newDateCompleted);
            return newDateCompleted;
        }
        return found;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(difficulty);
        dest.writeLong(goalTime);
        dest.writeLong(elapsedTime);
        dest.writeInt(daysRepeat);
        dest.writeString(new DatesConverter().fromDatesList(datesCompleted));
    }

    public static final Parcelable.Creator<Habit> CREATOR = new Parcelable.Creator<Habit>() {
        @Override
        public Habit createFromParcel(Parcel source) {
            long id = source.readLong();
            String name = source.readString();
            int difficult = source.readInt();
            long goalTime = source.readLong();
            long elapsedTime = source.readLong();
            int daysRepeat = source.readInt();
            List<DateCompleted> datesList = new DatesConverter().toDatesList(source.readString());
            return new Habit(id, name, difficult, goalTime, elapsedTime, daysRepeat, datesList);
        }

        @Override
        public Habit[] newArray(int size) {
            return new Habit[size];
        }
    };
}