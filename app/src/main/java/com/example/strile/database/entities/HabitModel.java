package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.TypeConverters;

import com.example.strile.App;
import com.example.strile.database.models.CompleteCaseDatabaseModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.converters.DatesConverter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.structures.DateCompleted;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@SuppressLint("ParcelCreator")
@Entity(tableName = "habit")
public class HabitModel extends CaseModel {

    private int goalTimeSeconds;
    private int elapsedTimeSeconds;
    private int daysRepeat;

    @TypeConverters({DatesConverter.class})
    private List<DateCompleted> datesCompleted = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabitModel that = (HabitModel) o;
        if (datesCompleted.size() != that.datesCompleted.size()) return false;
        datesCompleted.sort((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        that.datesCompleted.sort((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        for (int i = 0; i < datesCompleted.size(); i++) {
            if (datesCompleted.get(i).getDate() != that.datesCompleted.get(i).getDate()) return false;
            if (datesCompleted.get(i).isComplete() != that.datesCompleted.get(i).isComplete()) return false;
        }
        return goalTimeSeconds == that.goalTimeSeconds &&
                elapsedTimeSeconds == that.elapsedTimeSeconds &&
                daysRepeat == that.daysRepeat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalTimeSeconds, elapsedTimeSeconds, daysRepeat, datesCompleted);
    }

    @Override
    public boolean plannedForDay() {
        final long day = DateManager.getVisibleDay();
        return plannedForDay(day);
    }

    public boolean plannedForDay(long day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day);
        int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return (daysRepeat & (int) Math.pow(2, weekday)) == Math.pow(2, weekday);
    }

    @Override
    public void setState(boolean complete) {

        CompleteCaseDatabaseModel databaseModel = App.getInstance().getCompleteCaseModel();
        int experience;
        if (complete) experience = (getStreak() + 1) * (difficulty + 10);
        else experience = -1* (getStreak()) * (difficulty + 10);
        long date = DateManager.getDateWithoutTime(DateManager.getVisibleDay()).getTime();
        databaseModel.insertCompleteCase(new CompleteCaseModel(name, date, experience, HabitModel.class.getCanonicalName()), null);
        App.getInstance().addExperience(experience);

        getCurrentDateCompleted().setComplete(complete);
        if (DateManager.getVisibleDay() == DateManager.getDateWithoutTime(Calendar.getInstance().getTimeInMillis()).getTime() && goalTimeSeconds != 0) {
            if (complete) elapsedTimeSeconds = goalTimeSeconds;
            else  elapsedTimeSeconds = 0;
        }

        notifyOfChanges();
    }

    @Override
    public boolean isCompleted() {
        if (DateManager.getVisibleDay() == DateManager.getDateWithoutTime(Calendar.getInstance().getTimeInMillis()).getTime() && goalTimeSeconds != 0 && elapsedTimeSeconds == goalTimeSeconds) {
                getCurrentDateCompleted().setComplete(true);
        }
        return getCurrentDateCompleted().isComplete();
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
        return getStreak(DateManager.getVisibleDay());
    }

    public int getCurrentStreak() {
        return getStreak(DateManager.getDateWithoutTime(Calendar.getInstance().getTimeInMillis()).getTime());
    }

    private int getStreak(long day) {
        datesCompleted.sort((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        int streak = 0;
        if (isCompleted()) streak++;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day);

        for (DateCompleted dateCompleted : datesCompleted) {
            if (dateCompleted.getDate() < day) {
                while (DateManager.getDateWithoutTime(calendar.getTimeInMillis()).getTime() != dateCompleted.getDate()) {
                    calendar.add(Calendar.DATE, -1);
                    if (DateManager.getDateWithoutTime(calendar.getTimeInMillis()).getTime() == dateCompleted.getDate())
                        break;
                    if (plannedForDay(calendar.getTimeInMillis())) return streak;
                }
                if (dateCompleted.isComplete()) streak++;
                else if (plannedForDay(dateCompleted.getDate())) break;
            }
        }
        return streak;
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

    private DateCompleted getCurrentDateCompleted() {
        DateCompleted found = datesCompleted.stream().filter(date -> DateManager.getVisibleDay() == date.getDate()).findAny().orElse(null);
        if (found == null) {
            DateCompleted newDateCompleted = new DateCompleted(DateManager.getVisibleDay(), false);
            datesCompleted.add(newDateCompleted);
            if (DateManager.getVisibleDay() == DateManager.getDateWithoutTime(Calendar.getInstance().getTimeInMillis()).getTime()) {
                elapsedTimeSeconds = 0;
            }
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
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(difficulty);
        dest.writeInt(goalTimeSeconds);
        dest.writeInt(elapsedTimeSeconds);
        dest.writeInt(daysRepeat);
        dest.writeString(new DatesConverter().fromDatesList(datesCompleted));
    }

    public static final Parcelable.Creator<HabitModel> CREATOR = new Parcelable.Creator<HabitModel>() {

        @Override
        public HabitModel createFromParcel(Parcel source) {
            HabitModel habitModel = new HabitModel();
            habitModel.setName(source.readString());
            habitModel.setId(source.readInt());
            habitModel.setDifficulty(source.readInt());
            habitModel.setGoalTimeSeconds(source.readInt());
            habitModel.setElapsedTimeSeconds(source.readInt());
            habitModel.setDaysRepeat(source.readInt());
            habitModel.setDatesCompleted(new DatesConverter().toDatesList(source.readString()));
            return habitModel;
        }

        @Override
        public HabitModel[] newArray(int size) {
            return new HabitModel[size];
        }
    };

    @Override
    public int getType() {
        return BaseModel.HABIT_TYPE;
    }
}