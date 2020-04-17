package com.example.strile.activities.add_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.strile.R;
import com.example.strile.fragments.dialog.DialogTimeGoalOptions;
import com.example.strile.sevice.DifficultyManager;
import com.example.strile.sevice.call_back_interfaces.CompleteDialogTimeGoalCallback;
import com.example.strile.sevice.presenter.PresenterManager;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    private AddTaskPresenter presenter;

    private View doneButton;
    private View backButton;
    private EditText nameTask;
    private EditText descriptionTask;
    private View deadlineButton;
    private TextView deadlineText;
    private View deadlineClearButton;
    private SeekBar seekBarDifficult;
    private TextView textDifficult;
    private FrameLayout errorLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            presenter = new AddTaskPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }

        setContentView(R.layout.activity_add_task);

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
        nameTask = findViewById(R.id.editText_name);
        nameTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.nameChanged(nameTask.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        descriptionTask = findViewById(R.id.editText_description);
        descriptionTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.descriptionChanged(descriptionTask.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        deadlineButton = findViewById(R.id.view_deadline);
        deadlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deadlineButtonClicked();
            }
        });
        deadlineText = findViewById(R.id.text_deadline);
        deadlineClearButton = findViewById(R.id.image_deadlineClear);
        deadlineClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deadlineClearButtonClicked();
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
        errorLine = findViewById(R.id.frame_error_line);
    }

    public static void start(Fragment caller) {
        Intent intent = new Intent(caller.getContext(), AddTaskActivity.class);
        caller.startActivity(intent);
    }

    void setVisibleErrorLineTextName(boolean visible) {
        if (visible) errorLine.setVisibility(View.VISIBLE);
        else errorLine.setVisibility(View.GONE);
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

    void setDeadlineText(String text) {
        deadlineText.setText(text);
    }

    void openDialogSetDate(long milliseconds) {
        final Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(milliseconds);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateAndTime.set(year, month, dayOfMonth, 0, 0, 0);
                presenter.deadlineChanged(dateAndTime.getTimeInMillis());
            }
        },
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate((new Date().getTime() ));
        dialog.show();
    }

    void setVisibleDeadlineClearButton(boolean visible) {
        if (visible) deadlineClearButton.setVisibility(View.VISIBLE);
        else deadlineClearButton.setVisibility(View.GONE);
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
