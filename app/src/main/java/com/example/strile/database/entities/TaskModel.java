package com.example.strile.database.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.converters.SubtasksConverter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressLint("ParcelCreator")
@Entity(tableName = "task")
public class TaskModel extends CaseModel {

    private String description = "";
    private long dateCreate;
    private long deadline;
    private long dateComplete;

    @TypeConverters({SubtasksConverter.class})
    private List<SubtaskModel> subtasks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskModel taskModel = (TaskModel) o;
        if (subtasks.size() != taskModel.subtasks.size()) return false;
        for (int i = 0; i < subtasks.size(); i++) {
            if (subtasks.get(i).getText() != taskModel.subtasks.get(i).getText()) return false;
            if (subtasks.get(i).isComplete() != taskModel.subtasks.get(i).isComplete()) return false;
        }
        return dateCreate == taskModel.dateCreate &&
                deadline == taskModel.deadline &&
                dateComplete == taskModel.dateComplete &&
                description.equals(taskModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, dateCreate, deadline, dateComplete);
    }

    @Override
    public void setState(boolean complete) {
        Log.d("MY", String.valueOf(complete));
        if (complete) {
            dateComplete = DateManager.getVisibleDay();
        } else {
            dateComplete = 0;
        }
    }

    @Override
    public boolean plannedForDay() {
        return dateCreate <= DateManager.getVisibleDay() && (dateComplete == 0 || dateComplete >= DateManager.getVisibleDay());
    }

    @Override
    public boolean isCompleted() {
        return dateComplete == DateManager.getVisibleDay();
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

    public List<SubtaskModel> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubtaskModel> subtasks) {
        this.subtasks = subtasks;
    }

    public long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(long dateCreate) {
        this.dateCreate = dateCreate;
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
        dest.writeLong(dateCreate);
        dest.writeString(new SubtasksConverter().fromList(subtasks));
    }

    public static final Parcelable.Creator<TaskModel> CREATOR = new Parcelable.Creator<TaskModel>() {

        @Override
        public TaskModel createFromParcel(Parcel source) {
            TaskModel taskModel = new TaskModel();
            taskModel.setName(source.readString());
            taskModel.setId(source.readInt());
            taskModel.setDifficulty(source.readInt());
            taskModel.setDescription(source.readString());
            taskModel.setDeadline(source.readLong());
            taskModel.setDateComplete(source.readLong());
            taskModel.setDateCreate(source.readLong());
            taskModel.setSubtasks(new SubtasksConverter().toList(source.readString()));
            return taskModel;
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };

    @Override
    public int getType() {
        return BaseModel.TASK_TYPE;
    }
}
