package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.strile.sevice.converters.SubtasksConverter;
import com.example.strile.sevice.date.Day;
import com.example.strile.sevice.settings.Difficulty;
import com.example.strile.sevice.structures.Subtask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressLint("ParcelCreator")
@Entity(tableName = "task")
public class Task implements Parcelable {

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            long id = source.readLong();
            String name = source.readString();
            int difficulty = source.readInt();
            String description = source.readString();
            long dateCreate = source.readLong();
            long deadline = source.readLong();
            long dateComplete = source.readLong();
            List<Subtask> subtasks = new SubtasksConverter().toList(source.readString());
            return new Task(id, name, difficulty, description, dateCreate, deadline, dateComplete, subtasks);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    private final long dateCreate;
    @TypeConverters({SubtasksConverter.class})
    private final List<Subtask> subtasks;
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int difficulty;
    private String description;
    private long deadline;
    private long dateComplete;

    @Ignore
    public Task() {
        name = "";
        difficulty = Difficulty.maxDifficulty / 3;
        description = "";
        dateCreate = Day.getDateOfDayWithoutTime(new Date()).getTime();
        deadline = 0;
        dateComplete = 0;
        subtasks = new ArrayList<>();
    }

    public Task(long id, String name, int difficulty, String description, long dateCreate,
                long deadline, long dateComplete, @NonNull List<Subtask> subtasks) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.description = description;
        this.dateCreate = dateCreate;
        this.deadline = deadline;
        this.dateComplete = dateComplete;
        this.subtasks = subtasks;
    }

    public boolean plannedForDay(Date date) {
        return dateCreate <= date.getTime() && (dateComplete == 0 || dateComplete >= date.getTime());
    }

    public boolean isCompleteOnDay(Date date) {
        return dateComplete == date.getTime();
    }

    public boolean isDeadline() {
        return deadline != 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline.getTime();
    }

    public long getDateComplete() {
        return dateComplete;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public long getDateCreate() {
        return dateCreate;
    }

    public void setStateForDay(boolean state, Date date) {
        dateComplete = 0;
        if (state) dateComplete = date.getTime();
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
        dest.writeString(description);
        dest.writeLong(dateCreate);
        dest.writeLong(deadline);
        dest.writeLong(dateComplete);
        dest.writeString(new SubtasksConverter().fromList(subtasks));
    }
}