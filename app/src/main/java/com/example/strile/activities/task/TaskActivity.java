package com.example.strile.activities.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.strile.R;
import com.example.strile.activities.habit.HabitActivity;
import com.example.strile.database.entities.Habit;
import com.example.strile.database.entities.Task;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public static void start(Fragment caller, Task task) {
        Intent intent = new Intent(caller.getContext(), TaskActivity.class);
        intent.putExtra(Task.class.getCanonicalName(), task);
        caller.startActivity(intent);
    }

}
