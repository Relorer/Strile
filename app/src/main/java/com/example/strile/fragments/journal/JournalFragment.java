package com.example.strile.fragments.journal;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.strile.R;
import com.example.strile.activities.add_habit.AddHabitActivity;
import com.example.strile.activities.add_task.AddTaskActivity;
import com.example.strile.sevice.presenter.PresenterManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class JournalFragment extends Fragment {

    private JournalPresenter presenter;

    private ProgressBar progressBar;
    private LinearLayout linearProgressBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FrameLayout toggle;
    private FloatingActionButton addButton;

    private ConstraintLayout.LayoutParams params;
    private boolean progressBarVisible;
    private float startY = 0;
    private int minMargin;
    private int maxMargin;
    private int countMove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        linearProgressBar = view.findViewById(R.id.linear_progressBar);
        progressBar = view.findViewById(R.id.progressBar);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(new JournalPagerAdapter(getChildFragmentManager()));
        tabLayout = view.findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        toggle = view.findViewById(R.id.frameLayout_toggle);
        setToggleClickListener();
        addButton = view.findViewById(R.id.addButton);
        setAddButtonClickListener();

        minMargin = getResources().getDimensionPixelOffset(R.dimen.margin_vertical_progress_bar_block_hide);
        maxMargin = getResources().getDimensionPixelOffset(R.dimen.margin_vertical_progress_bar_block_visible);
        progressBarVisible = false;
        params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.topToBottom = R.id.tab;

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            presenter = new JournalPresenter();
        } else {
            presenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }

    void startAddHabitFragment() {
        AddHabitActivity.start(this);
    }

    void startAddTaskFragment() {
        AddTaskActivity.start(this);
    }

    private void setAddButtonClickListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addButtonClicked(viewPager.getAdapter().getPageTitle(viewPager.getCurrentItem()).toString().trim());
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setToggleClickListener() {
        toggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float delta = (float) ((event.getY() - startY) / 2);
                int margin = progressBarVisible ? (int) delta + maxMargin : (int) delta + minMargin;
                margin = Math.min(margin, maxMargin);
                margin = Math.max(margin, minMargin);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getY();
                        params.topMargin = margin;
                        linearProgressBar.setLayoutParams(params);
                        countMove = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        params.topMargin = margin;
                        countMove++;
                        linearProgressBar.setLayoutParams(params);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (countMove > 1)
                            margin = margin - minMargin < maxMargin - margin ? minMargin : maxMargin;
                        else
                            margin = progressBarVisible ? minMargin : maxMargin;
                        progressBarVisible = margin != minMargin;

                        ValueAnimator animator = ValueAnimator.ofInt(params.topMargin, margin);
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                params.topMargin = (int) animation.getAnimatedValue();
                                linearProgressBar.setLayoutParams(params);
                            }
                        });
                        animator.start();
                        break;
                }
                return true;
            }
        });
    }
}
