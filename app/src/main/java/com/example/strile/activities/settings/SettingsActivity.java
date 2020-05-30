package com.example.strile.activities.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.activities.settings.pomodoro_timer.SettingsPomodoroTimerActivity;
import com.example.strile.service.presenter.PresenterManager;
import com.example.strile.service.recycler_view_adapter.adapters.SettingsListAdapter;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.button_night_mode_selection.ButtonNightModeSelectionModel;
import com.example.strile.service.recycler_view_adapter.items.button_pomodoro_timer_settings.ButtonPomodoroTimerSettingsModel;
import com.example.strile.service.recycler_view_adapter.items.button_task_time_notify_selection.ButtonTaskTimeNotifySelectionModel;
import com.example.strile.service.recycler_view_adapter.items.switch_setting.SwitchSettingModel;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private SettingsPresenter presenter;
    private SettingsListAdapter adapter;

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, SettingsActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new SettingsPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_with_app_bar);

        TextView textTitle = findViewById(R.id.text_title);
        ImageView imageSpecialPurposeLeft = findViewById(R.id.image_special_purpose_button_left);
        View backButton = findViewById(R.id.special_purpose_button_left);
        RecyclerView recyclerView = findViewById(R.id.recycler);

        textTitle.setText(R.string.settings);
        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                R.drawable.back_arrow, null));
        backButton.setOnClickListener(v -> presenter.backButtonClicked());

        adapter = new SettingsListAdapter(model -> {
            if (model instanceof ButtonNightModeSelectionModel) {
                presenter.nightModeChanged((ButtonNightModeSelectionModel) model);
            } else if (model instanceof SwitchSettingModel) {
                presenter.switchStateChanged((SwitchSettingModel) model);
            } else if (model instanceof ButtonTaskTimeNotifySelectionModel) {
                presenter.taskTimeNotifyChanged((ButtonTaskTimeNotifySelectionModel) model);
            }
        }, sender -> {
            if (sender instanceof ButtonPomodoroTimerSettingsModel) {
                presenter.buttonPomodoroTimerSettingsClicked((ButtonPomodoroTimerSettingsModel) sender);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
    }

    public void startPomodoroTimerSettingsActivity() {
        SettingsPomodoroTimerActivity.start(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}
