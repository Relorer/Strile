package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.strile.sevice.converters.SubtasksConverter;
import com.example.strile.sevice.structures.Subtask;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ParcelCreator")
@Entity(tableName = "task")
public class Task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final String name;
    private final int difficulty;
    private final String description;
    private final long dateCreate;
    private final long deadline;
    private final long dateComplete;

    @TypeConverters({SubtasksConverter.class})
    private final List<Subtask> subtasks;

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

    public boolean plannedForDay(long date) {
        return dateCreate <= date && (dateComplete == 0 || dateComplete >= date);
    }

    public boolean isCompleteOnDay(long date) {
        return dateComplete == date;
    }

    public boolean isDeadline() {
        return deadline != 0;
    }

    public String getDescription() {
        return description;
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

    public long getDeadline() {
        return deadline;
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

    public Task setStateForDay(boolean state, long date) {
        long dateComplete = 0;
        if (state) dateComplete = date;
        return new Task(id, name, difficulty, description, dateCreate, deadline, dateComplete, new ArrayList<>(subtasks));
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
}