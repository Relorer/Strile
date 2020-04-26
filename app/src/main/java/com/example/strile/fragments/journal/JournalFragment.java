package com.example.strile.fragments.journal;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.strile.R;
import com.example.strile.activities.case_activity.add_case.add_habit.AddHabitActivity;
import com.example.strile.activities.case_activity.add_case.add_task.AddTaskActivity;
import com.example.strile.fragments.journal.cases.habits.JournalHabitsFragment;
import com.example.strile.fragments.journal.cases.tasks.JournalTasksFragment;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.presenter.PresenterManager;
import com.example.strile.sevice.recycler_view_adapter.adapters.DaysListAdapter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.DayModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class JournalFragment extends Fragment {

    private JournalPresenter presenter;
    private JournalHabitsFragment journalHabitsFragment = new JournalHabitsFragment();
    private JournalTasksFragment journalTasksFragment = new JournalTasksFragment();

    DaysListAdapter daysListAdapter = new DaysListAdapter();
    JournalPagerAdapter journalPagerAdapter;

    private LinearLayout linearTopBlock;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FrameLayout toggle;
    private FloatingActionButton addButton;

    private ConstraintLayout.LayoutParams params;
    private boolean topBlockVisible;
    private float startY = 0;
    private int minMargin;
    private int maxMargin;
    private int countMove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        linearTopBlock = view.findViewById(R.id.linear_topBlock);
        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tab);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        toggle = view.findViewById(R.id.frameLayout_toggle);
        addButton = view.findViewById(R.id.addButton);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(journalHabitsFragment);
        fragments.add(journalTasksFragment);
        journalPagerAdapter = new JournalPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(journalPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setToggleClickListener();
        addButton.setOnClickListener(v -> presenter.addButtonClicked(viewPager.getAdapter().getPageTitle(viewPager.getCurrentItem()).toString().trim()));

        minMargin = getResources().getDimensionPixelOffset(R.dimen.margin_vertical_journal_top_block_hide);
        maxMargin = getResources().getDimensionPixelOffset(R.dimen.margin_vertical_journal_top_block_visible);
        topBlockVisible = false;
        params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.topToBottom = R.id.tab;

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = view.getResources().getDimensionPixelSize(R.dimen.margin_base) / 2;
                outRect.right = view.getResources().getDimensionPixelSize(R.dimen.margin_base) / 2;
            }
        });
        daysListAdapter.setOnClickItemListener(sender -> {
            if (sender instanceof DayModel)
                presenter.dayClicked((DayModel) sender);
        });
        recyclerView.setAdapter(daysListAdapter);
        recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

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

    void setSortedListDays(@NonNull List<BaseModel> items) {
        daysListAdapter.setItems(items);
    }

    void startAddHabitFragment() {
        AddHabitActivity.start(this);
    }

    void startAddTaskFragment() {
        AddTaskActivity.start(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setToggleClickListener() {
        toggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float delta = (float) ((event.getY() - startY) / 2);
                int margin = topBlockVisible ? (int) delta + maxMargin : (int) delta + minMargin;
                margin = Math.min(margin, maxMargin);
                margin = Math.max(margin, minMargin);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getY();
                        params.topMargin = margin;
                        linearTopBlock.setLayoutParams(params);
                        countMove = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        params.topMargin = margin;
                        countMove++;
                        linearTopBlock.setLayoutParams(params);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (countMove > 1)
                            margin = margin - minMargin < maxMargin - margin ? minMargin : maxMargin;
                        else
                            margin = topBlockVisible ? minMargin : maxMargin;
                        topBlockVisible = margin != minMargin;

                        ValueAnimator animator = ValueAnimator.ofInt(params.topMargin, margin);
                        animator.addUpdateListener(animation -> {
                            params.topMargin = (int) animation.getAnimatedValue();
                            linearTopBlock.setLayoutParams(params);
                        });
                        animator.start();
                        break;
                }
                return true;
            }
        });
    }
}
