package com.example.strile.activities.add_habit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strile.R;
import com.example.strile.fragments.dialog.DialogRepeatOptions;
import com.example.strile.fragments.dialog.DialogTimeGoalOptions;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.call_back_interfaces.CompleteDialogRepeatCallback;
import com.example.strile.sevice.call_back_interfaces.CompleteDialogTimeGoalCallback;
import com.example.strile.sevice.presenter.PresenterManager;

import java.util.List;

public class AddHabitActivity extends AppCompatActivity {

    private AddHabitPresenter presenter;

    private View doneButton;
    private View backButton;
    private EditText nameHabit;
    private View repeatButton;
    private View goalTimeButton;
    private TextView goalTimeText;
    private TextView repeatText;
    private SeekBar seekBarDifficult;
    private TextView textDifficult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new AddHabitPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_add_habit);

        doneButton = findViewById(R.id.image_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doneButtonClicked();
            }
        });
        backButton = findViewById(R.id.image_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backButtonClicked();
            }
        });
        nameHabit = findViewById(R.id.editText_name);
        nameHabit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.nameChanged(nameHabit.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        repeatText = findViewById(R.id.text_repeat);
        repeatButton = findViewById(R.id.view_repeat);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.repeatButtonClicked();
            }
        });
        goalTimeText = findViewById(R.id.text_goal);
        goalTimeButton = findViewById(R.id.view_goalTime);
        goalTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goalTimeButtonClicked();
            }
        });
        textDifficult = findViewById(R.id.text_difficult);
        seekBarDifficult = findViewById(R.id.seekBar_difficult);
        seekBarDifficult.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                presenter.difficultChanged(progress);
                Drawable thumb = seekBar.getThumb();
                int max = seekBar.getMax();
                int difficulty = seekBar.getProgress();
                thumb.setColorFilter(getColor(DifficultyManager.getColor(difficulty)), PorterDuff.Mode.SRC_ATOP);
                seekBar.setThumb(thumb);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public static void start(Fragment caller) {
        Intent intent = new Intent(caller.getContext(), AddHabitActivity.class);
        caller.startActivity(intent);
    }

    void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    void setGoalTimeText(String text) {
        goalTimeText.setText(text);
    }

    void setRepeatText(String text) {
        repeatText.setText(text);
    }

    void setRepeatTextColor(int color) {
        repeatText.setTextColor(getColor(color));
    }

    void setTextDifficult(String text) {
        textDifficult.setText(text);
    }

    void setTextDifficultColor(int color) {
        textDifficult.setTextColor(getColor(color));
    }

    void setSeekBarDifficultMaximum(int maximum) {
        seekBarDifficult.setMax(maximum);
    }

    void setSeekBarDifficultProgress(int progress) {
        seekBarDifficult.setProgress(progress);
    }

    void openDialogTimeGoalOptions(int timeSeconds) {
        FragmentManager manager = getSupportFragmentManager();
        DialogTimeGoalOptions dialog = new DialogTimeGoalOptions(timeSeconds, new CompleteDialogTimeGoalCallback() {
            @Override
            public void onComplete(int timeSeconds) {
                presenter.goalTimeChanged(timeSeconds);
            }
        });
        dialog.show(manager, "TimeGoal");
    }

    void openDialogRepeatOptions(boolean[] checkedDays) {
        FragmentManager manager = getSupportFragmentManager();
        DialogRepeatOptions dialog = new DialogRepeatOptions(checkedDays, new CompleteDialogRepeatCallback() {
            @Override
            public void onComplete(boolean[] checkedDays) {
                presenter.repeatDaysChanged(checkedDays);
            }
        });
        dialog.show(manager, "Repeat");
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