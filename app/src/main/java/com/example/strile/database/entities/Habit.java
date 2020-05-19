package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.strile.sevice.converters.DatesConverter;
import com.example.strile.sevice.date.Day;
import com.example.strile.sevice.settings.Difficulty;
import com.example.strile.sevice.structures.DateCompleted;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressLint("ParcelCreator")
@Entity(tableName = "habit")
public class Habit implements Parcelable {

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
    @TypeConverters({DatesConverter.class})
    private final List<DateCompleted> datesCompleted;
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int difficulty;
    private long goalTime;
    private long elapsedTime;
    private int daysRepeat;

    public Habit(long id, String name, int difficulty, long goalTime, long elapsedTime,
                 int daysRepeat, @NonNull List<DateCompleted> datesCompleted) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.goalTime = goalTime;
        this.elapsedTime = elapsedTime;
        this.daysRepeat = daysRepeat;
        this.datesCompleted = datesCompleted;
        getDateCompleted(new Date());
    }

    @Ignore
    public Habit(String name, int difficulty, long goalTime, long elapsedTime,
                 boolean[] daysRepeat, @NonNull List<DateCompleted> datesCompleted) {
        this.name = name;
        this.difficulty = difficulty;
        this.goalTime = goalTime;
        this.elapsedTime = elapsedTime;
        this.daysRepeat = arrayRepeatToInt(daysRepeat);
        this.datesCompleted = datesCompleted;
    }

    @Ignore
    public Habit() {
        name = "";
        difficulty = Difficulty.maxDifficulty / 3;
        goalTime = 0;
        elapsedTime = 0;
        daysRepeat = arrayRepeatToInt(new boolean[]{true, true, true, true, true, true, true});
        datesCompleted = new ArrayList<>();
    }

    public boolean plannedForDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
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

    public void setName(String name) {
        this.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public long getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(long goalTime) {
        Date day = Day.getDateOfDayWithoutTime(new Date());
        if (elapsedTime < goalTime && isCompleteOnDay(day)) {
            setStateForDay(false, day);
        } else if (elapsedTime >= goalTime && !isCompleteOnDay(day) && goalTime > 0) {
            setStateForDay(true, day);
        }
        this.goalTime = goalTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        Date day = Day.getDateOfDayWithoutTime(new Date());
        if (elapsedTime >= goalTime && !isCompleteOnDay(day) && goalTime > 0) {
            setStateForDay(true, day);
        }
        this.elapsedTime = elapsedTime;
    }

    public int getDaysRepeat() {
        return daysRepeat;
    }

    public void setDaysRepeat(boolean[] daysRepeat) {
        this.daysRepeat = arrayRepeatToInt(daysRepeat);
    }

    public List<DateCompleted> getDatesCompleted() {
        return datesCompleted;
    }

    public int getStreakByDay(Date date) {
        date = Day.getDateOfDayWithoutTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        datesCompleted.sort((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        int streak = 0;
        for (DateCompleted item : datesCompleted) {

            final long itemDate = item.getDate();
            if (itemDate <= date.getTime()) {
                while (calendar.getTimeInMillis() != itemDate) {
                    calendar.add(Calendar.DATE, -1);
                    if (calendar.getTimeInMillis() == itemDate) break;
                    if (plannedForDay(calendar.getTime())) return streak;
                }
                if (item.isComplete()) streak++;
                else if (itemDate != date.getTime() && plannedForDay(new Date(itemDate))) break;
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

    public void setStateForDay(boolean state, Date date) {
        DateCompleted dateCompleted = getDateCompleted(date);
        dateCompleted.setState(state);
    }

    private int arrayRepeatToInt(boolean[] daysRepeat) {
        int repeat = 0;
        for (int i = 0; i < daysRepeat.length; i++) {
            if (daysRepeat[i]) repeat += Math.pow(2, i);
        }
        return repeat;
    }

    private DateCompleted getDateCompleted(Date date) {
        final Date dateOfDayWithoutTime = Day.getDateOfDayWithoutTime(date);
        DateCompleted found = datesCompleted.stream()
                .filter(item -> dateOfDayWithoutTime.getTime() == item.getDate())
                .findAny()
                .orElse(null);
        if (found == null) {
            DateCompleted newDateCompleted = new DateCompleted(dateOfDayWithoutTime.getTime(), false);
            datesCompleted.add(newDateCompleted);
            if (dateOfDayWithoutTime.getTime() == Day.getDateOfDayWithoutTime(new Date()).getTime())
                elapsedTime = 0;
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
}