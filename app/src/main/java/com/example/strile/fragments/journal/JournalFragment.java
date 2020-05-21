package com.example.strile.fragments.journal;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.strile.R;
import com.example.strile.activities.case_activity.add_case.add_habit.AddHabitActivity;
import com.example.strile.activities.case_activity.add_case.add_task.AddTaskActivity;
import com.example.strile.fragments.journal.cases.JournalCasesFragment;
import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.habits.JournalHabitsFragment;
import com.example.strile.fragments.journal.cases.tasks.JournalTasksFragment;
import com.example.strile.service.presenter.PresenterManager;
import com.example.strile.service.recycler_view_adapter.adapters.DaysListAdapter;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.day.DayModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class JournalFragment extends Fragment {

    private final List<Fragment> pages = new ArrayList<>();
    private JournalPresenter presenter;
    private DaysListAdapter daysListAdapter;
    private boolean topBlockVisible = false;
    private int countMove;
    private float startY = 0;

    private Snackbar snackbar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        final TabLayout tabLayout = view.findViewById(R.id.tab);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);
        final FloatingActionButton addButton = view.findViewById(R.id.addButton);
        final LinearLayout linearTopBlock = view.findViewById(R.id.linear_topBlock);
        final ViewPager viewPager = view.findViewById(R.id.pager);
        final FrameLayout toggle = view.findViewById(R.id.frameLayout_toggle);

        final int minMargin = getResources().getDimensionPixelOffset(R.dimen.margin_vertical_journal_top_block_hide);
        final int maxMargin = getResources().getDimensionPixelOffset(R.dimen.margin_vertical_journal_top_block_visible);

        pages.add(new JournalHabitsFragment());
        pages.add(new JournalTasksFragment());
        JournalPagerAdapter journalPagerAdapter = new JournalPagerAdapter(getChildFragmentManager(), pages);

        daysListAdapter = new DaysListAdapter(sender -> presenter.dayClicked((DayModel) sender), model -> {
        });

        viewPager.setAdapter(journalPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        addButton.setOnClickListener(v -> presenter.addButtonClicked(
                (JournalCasesPage) journalPagerAdapter.getItem(viewPager.getCurrentItem())
        ));

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = view.getResources().getDimensionPixelSize(R.dimen.margin_base) / 2;
                outRect.right = view.getResources().getDimensionPixelSize(R.dimen.margin_base) / 2;
            }
        });
        recyclerView.setAdapter(daysListAdapter);
        recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        final ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.topToBottom = R.id.tab;
        toggle.setOnTouchListener((v, event) -> {
            final float delta = (event.getY() - startY) / 2;
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

                    final ValueAnimator animator = ValueAnimator.ofInt(params.topMargin, margin);
                    animator.addUpdateListener(animation -> {
                        params.topMargin = (int) animation.getAnimatedValue();
                        linearTopBlock.setLayoutParams(params);
                    });
                    animator.start();
                    break;
            }
            return true;
        });

        return view;
    }

    void setSortedListDays(@NonNull List<BaseModel> items) {
        daysListAdapter.setItems(items);
    }

    void startAddHabitFragment() {
        AddHabitActivity.start(this.getActivity());
    }

    void startAddTaskFragment() {
        AddTaskActivity.start(this.getActivity());
    }

    public void setVisibleDayOnPages(Date day) {
        for (int i = 0; i < pages.size(); i++) {
            ((JournalCasesFragment) pages.get(i)).setVisibleDay(day);
        }
    }

    public void dismissSnackbar() {
        if (snackbar != null) snackbar.dismiss();
    }

    public void showSnackbar(String text, String actionName, View.OnClickListener onClickListener) {
        if (getView() != null) {
            snackbar = Snackbar.make(getView().findViewById(R.id.coordinator_journal), text, 5000)
                    .setAction(actionName, onClickListener);

            snackbar.setBackgroundTint(ContextCompat.getColor(Objects.requireNonNull(this.getContext()), R.color.colorPrimary));
            snackbar.setTextColor(ContextCompat.getColor(Objects.requireNonNull(this.getContext()), R.color.colorBlack));

            snackbar.show();
        }
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
}