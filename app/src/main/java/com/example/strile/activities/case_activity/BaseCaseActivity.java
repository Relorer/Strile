package com.example.strile.activities.case_activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.sevice.presenter.PresenterManager;
import com.example.strile.sevice.recycler_view_adapter.adapters.CaseActivityListAdapter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonAddSubtaskModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonDateSelectionModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;
import com.example.strile.sevice.recycler_view_adapter.models.EditTextModel;
import com.example.strile.sevice.recycler_view_adapter.models.ProgressBarElapsedTimeModel;
import com.example.strile.sevice.recycler_view_adapter.models.SeekBarDifficultModel;
import com.example.strile.sevice.recycler_view_adapter.models.SubtaskModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public abstract class BaseCaseActivity extends AppCompatActivity {
    private static Fragment lastCaller;

    private BaseCasePresenter presenter;

    private CaseActivityListAdapter adapter = new CaseActivityListAdapter();

    protected TextView textTitle;
    protected ImageView imageSpecialPurposeRight;
    protected ImageView imageSpecialPurposeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = getNewPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_case);

        textTitle = findViewById(R.id.text_title);
        imageSpecialPurposeRight = findViewById(R.id.image_special_purpose_button_right);
        imageSpecialPurposeLeft = findViewById(R.id.image_special_purpose_button_left);
        View specialPurposeButton = findViewById(R.id.special_purpose_button_right);
        View backButton = findViewById(R.id.special_purpose_button_left);
        RecyclerView recycler = findViewById(R.id.recycler);

        imageSpecialPurposeLeft.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back_arrow, null));

        specialPurposeButton.setOnClickListener(v -> presenter.specialPurposeButtonClicked());
        backButton.setOnClickListener(v -> presenter.backButtonClicked());

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.setHasStableIds(true);
        recycler.setAdapter(adapter);

        adapter.setOnModelChangedListener(model -> {
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
            }
        });

        adapter.setOnClickItemListener(sender -> {
            if (sender instanceof ButtonAddSubtaskModel) {
                presenter.addSubtaskButtonClicked((ButtonAddSubtaskModel) sender);
            } else if (sender instanceof ProgressBarElapsedTimeModel) {
                presenter.elapsedTimeClicked((ProgressBarElapsedTimeModel)sender);
            }
        });

    }

    public void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    public void showSnackbar(Fragment fragment, String text, String actionName, View.OnClickListener onClickListener) {
        if (fragment != null) {
            Snackbar snackbar = Snackbar.make(fragment.getView(), text, 5000)
                    .setAction(actionName, onClickListener);

            View view = snackbar.getView();
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            int baseMargin = view.getResources().getDimensionPixelSize(R.dimen.margin_base);
            p.setMargins(baseMargin, 0, baseMargin, baseMargin * 3);
            view.requestLayout();
            snackbar.show();
        }
    }

    protected abstract BaseCasePresenter getNewPresenter();

    public static Fragment getCaller() {
        return lastCaller;
    }

    protected static void setCaller(Fragment caller) {
        lastCaller = caller;
    }

    protected BaseCasePresenter getPresenter() {
        return presenter;
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}