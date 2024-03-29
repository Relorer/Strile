package com.example.strile.ui.screens.settings.pomodoro_timer;

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
import com.example.strile.infrastructure.presenter.PresenterManager;
import com.example.strile.infrastructure.rvadapter.adapters.SettingsListAdapter;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.number_edit_with_title.NumberEditWIthTitleModel;

import java.util.List;

public class SettingsPomodoroTimerActivity extends AppCompatActivity {

    private SettingsPomodoroTimerPresenter presenter;
    private SettingsListAdapter adapter;

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, SettingsPomodoroTimerActivity.class);
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new SettingsPomodoroTimerPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_with_app_bar);

        TextView textTitle = findViewById(R.id.text_title);
        ImageView imageSpecialPurposeLeft = findViewById(R.id.image_special_purpose_button_left);
        View backButton = findViewById(R.id.special_purpose_button_left);
        RecyclerView recyclerView = findViewById(R.id.recycler);

        textTitle.setText(R.string.timer_setting);
        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                R.drawable.back_arrow, null));
        backButton.setOnClickListener(v -> presenter.backButtonClicked());

        adapter = new SettingsListAdapter(model -> {
            if (model instanceof NumberEditWIthTitleModel) {
                presenter.numberChanged((NumberEditWIthTitleModel) model);
            }
        }, sender -> {

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
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