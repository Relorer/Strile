package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.strile.R;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.converters.SubtasksConverter;
import com.example.strile.sevice.recycler_view_adapter.ItemModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressLint("ParcelCreator")
@Entity(tableName = "task")
public class Task extends Case {

    private String name = "";
    private String description = "";
    private int difficulty;
    private long deadline;
    private long dateComplete;

    @TypeConverters({SubtasksConverter.class})
    private List<String> subtasks = new ArrayList<>();

    @Override
    public void setState(boolean complete) {
        if (complete) {
            dateComplete = DateManager.getDateWithoutTimeUsingCalendar().getTime();
        }
        else {
            dateComplete = 0;
        }
    }

    @Override
    public boolean plannedForToday() {
        return dateComplete == 0 || dateComplete == DateManager.getDateWithoutTimeUsingCalendar().getTime();
    }

    @Override
    public boolean completed() {
        return dateComplete != 0;
    }

    public boolean isDeadline() {
        return deadline != 0;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public long getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(long dateComplete) {
        this.dateComplete = dateComplete;
    }

    public List<String> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<String> subtasks) {
        this.subtasks = subtasks;
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
        dest.writeString(description);
        dest.writeLong(deadline);
        dest.writeLong(dateComplete);
        dest.writeString(new SubtasksConverter().fromSubtasksList(subtasks));
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel source) {
            Task task = new Task();
            task.setName(source.readString());
            task.setId(source.readInt());
            task.setDifficulty(source.readInt());
            task.setDescription(source.readString());
            task.setDeadline(source.readLong());
            task.setDateComplete(source.readLong());
            task.setSubtasks(new SubtasksConverter().toDatesList(source.readString()));
            return task;
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int getType() {
        return ItemModel.TASK_TYPE;
    }
}
