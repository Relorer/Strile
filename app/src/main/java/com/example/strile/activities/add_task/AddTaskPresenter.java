package com.example.strile.activities.add_task;

import android.util.Log;

import com.example.strile.App;
import com.example.strile.R;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;
import com.example.strile.database.models.TaskModel;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.presenter.BasePresenter;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskPresenter extends BasePresenter<TaskModel, AddTaskActivity> {

    private Task task;

    AddTaskPresenter() {
        model = App.getInstance().getTaskModel();
        task = new Task();
        task.setDeadline(0);
        task.setDifficulty(DifficultyManager.maxDifficult / 3);
    }

    @Override
    protected void updateView() {
        view().setSeekBarDifficultMaximum(DifficultyManager.maxDifficult);
        view().setSeekBarDifficultProgress(task.getDifficulty());
        changeTextDifficult();
        changeTextDeadline();
        view().setVisibleDeadlineClearButton(task.isDeadline());
    }

    void doneButtonClicked() {
        if (!task.getName().equals("")) {
            addTaskInDatabase();
            view().finish();
        } else if (task.getName().equals("")) view().showToast("Task's name shouldn't be empty!");
    }

    void backButtonClicked() {
        view().finish();
    }

    void deadlineButtonClicked() {
        view().openDialogSetDate(task.getDeadline());
    }

    void deadlineClearButtonClicked() {
        task.setDeadline(0);
        changeTextDeadline();
        view().setVisibleDeadlineClearButton(task.isDeadline());
    }

    void nameChanged(String name) {
        task.setName(name.trim());
    }

    void descriptionChanged(String description) {
        task.setDescription(description.trim());
    }

    void deadlineChanged(long milliseconds) {
        task.setDeadline(milliseconds);
        changeTextDeadline();
        view().setVisibleDeadlineClearButton(task.isDeadline());
    }

    void difficultChanged(int difficult) {
        task.setDifficulty(difficult);
        changeTextDifficult();
    }

    private void addTaskInDatabase() {
        model.insertCase(task, null);
    }

    private void changeTextDeadline() {
        if (task.isDeadline()) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(task.getDeadline());
            view().setDeadlineText("By " + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR));
        } else {
            view().setDeadlineText("Add deadline");
        }
    }

    private void changeTextDifficult() {
        view().setTextDifficult(DifficultyManager.getName(task.getDifficulty()));
        view().setTextDifficultColor(DifficultyManager.getColor(task.getDifficulty()));
    }
}
