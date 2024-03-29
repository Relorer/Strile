package com.example.strile.ui.screens.case_activity;

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
import com.example.strile.infrastructure.rvadapter.adapters.CaseActivityListAdapter;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_add_subtask.ButtonAddSubtaskModel;
import com.example.strile.infrastructure.rvadapter.items.button_date_selection.ButtonDateSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.button_repeat.ButtonRepeatModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_goal.ButtonTimeGoalModel;
import com.example.strile.infrastructure.rvadapter.items.button_time_selection.ButtonTimeSelectionModel;
import com.example.strile.infrastructure.rvadapter.items.edit_text.EditTextModel;
import com.example.strile.infrastructure.rvadapter.items.progress_bar_elapsed_time.ProgressBarElapsedTimeModel;
import com.example.strile.infrastructure.rvadapter.items.seek_bar_difficult.SeekBarDifficultModel;
import com.example.strile.infrastructure.rvadapter.items.subtask.SubtaskModel;
import com.example.strile.utilities.ToastUtilities;

import java.util.List;

public abstract class BaseCaseActivity extends AppCompatActivity {

    protected TextView textTitle;
    protected ImageView imageSpecialPurposeRight;
    private BaseCasePresenter presenter;
    private CaseActivityListAdapter adapter;
    @SuppressWarnings("FieldCanBeLocal")
    private ImageView imageSpecialPurposeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = getNewPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_with_app_bar);

        textTitle = findViewById(R.id.text_title);
        imageSpecialPurposeRight = findViewById(R.id.image_special_purpose_button_right);
        imageSpecialPurposeLeft = findViewById(R.id.image_special_purpose_button_left);
        View specialPurposeButton = findViewById(R.id.special_purpose_button_right);
        View backButton = findViewById(R.id.special_purpose_button_left);
        RecyclerView recycler = findViewById(R.id.recycler);

        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                R.drawable.back_arrow, null));

        specialPurposeButton.setOnClickListener(v -> presenter.specialPurposeButtonClicked());
        backButton.setOnClickListener(v -> presenter.backButtonClicked());

        adapter = new CaseActivityListAdapter(sender -> {
            if (sender instanceof ButtonAddSubtaskModel) {
                presenter.addSubtaskButtonClicked();
            } else if (sender instanceof ProgressBarElapsedTimeModel) {
                presenter.elapsedTimeClicked();
            }
        }, model -> {
            if (model instanceof EditTextModel) {
                presenter.editTextChanged((EditTextModel) model);
            } else if (model instanceof SubtaskModel) {
                presenter.subtaskChanged((SubtaskModel) model);
            } else if (model instanceof SeekBarDifficultModel) {
                presenter.difficultChanged((SeekBarDifficultModel) model);
            } else if (model instanceof ButtonDateSelectionModel) {
                presenter.dateSelectionChanged((ButtonDateSelectionModel) model);
            } else if (model instanceof ButtonRepeatModel) {
                presenter.repeatChanged((ButtonRepeatModel) model);
            } else if (model instanceof ButtonTimeGoalModel) {
                presenter.timeGoalChanged((ButtonTimeGoalModel) model);
            } else if (model instanceof ButtonTimeSelectionModel) {
                presenter.notifyTimeChanged((ButtonTimeSelectionModel) model);
            }
        });
        adapter.setHasStableIds(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
    }

    public void showToast(String text) {
        ToastUtilities.Companion.showToastWithCenteredText(this, text);
    }

    protected abstract BaseCasePresenter getNewPresenter();

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