package com.example.strile.fragments.journal.cases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.activities.habit.HabitActivity;
import com.example.strile.database.entities.Case;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedCaseChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnClickCaseListener;
import com.example.strile.sevice.presenter.PresenterManager;

import java.util.List;

public abstract class JournalCasesFragment extends Fragment {
    private JournalCasesPresenter presenter;

    private JournalListAdapter adapterIncomplete = new JournalListAdapter();
    private JournalListAdapter adapterCompleted = new JournalListAdapter();

    private RecyclerView recyclerIncomplete;
    private RecyclerView recyclerCompleted;
    private View buttonHiding;
    private CheckBox buttonHidingCheckBox;
    private TextView buttonHidingText;
    private TextView buttonHidingCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journal_cases, container, false);

        recyclerIncomplete = view.findViewById(R.id.recycler_incomplete);
        recyclerCompleted = view.findViewById(R.id.recycler_completed);
        buttonHiding = view.findViewById(R.id.button_hiding);
        buttonHidingCheckBox = view.findViewById(R.id.checkbox_done);
        buttonHidingText = view.findViewById(R.id.text_state);
        buttonHidingCount = view.findViewById(R.id.text_count);

        recyclerIncomplete.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerCompleted.setLayoutManager(new LinearLayoutManager(view.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        recyclerIncomplete.setAdapter(adapterIncomplete);
        recyclerCompleted.setAdapter(adapterCompleted);

        adapterIncomplete.setOnClickItemListener(new OnClickCaseListener() {
            @Override
            public void onClick(Case c) {
                presenter.itemClicked(c);
            }
        });
        adapterIncomplete.setOnCheckedItemChangeListener(new OnCheckedCaseChangeListener() {
            @Override
            public void onCheckedChanged(Case c, boolean isChecked) {
                presenter.itemStateChanged(c);
            }
        });
        adapterCompleted.setOnClickItemListener(new OnClickCaseListener() {
            @Override
            public void onClick(Case c) {
                presenter.itemClicked(c);
            }
        });
        adapterCompleted.setOnCheckedItemChangeListener(new OnCheckedCaseChangeListener() {
            @Override
            public void onCheckedChanged(Case c, boolean isChecked) {
                presenter.itemStateChanged(c);
            }
        });


        buttonHiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.buttonHidingStateChanged();
            }
        });

        return view;
    }

    void setSortedListIncomplete(List<Case> cases) {
        adapterIncomplete.setSortedList(cases);
    }

    void setSortedListCompleted(List<Case> cases) {
        adapterCompleted.setSortedList(cases);
    }

    void setVisibleButtonHiding(boolean visible) {
        if (visible) buttonHiding.setVisibility(View.VISIBLE);
        else buttonHiding.setVisibility(View.INVISIBLE);
    }

    void setCheckedButtonHiding(boolean checked) {
        buttonHidingCheckBox.setChecked(checked);
        if (checked) {
            buttonHidingText.setText("Show Сompleted");
        } else {
            buttonHidingText.setText("Hide Сompleted");
        }
    }

    void updateButtonHidingCount(int count) {
        buttonHidingCount.setText(String.valueOf(count));
    }

    protected abstract void startCaseActivity(Case c);

    protected abstract JournalCasesPresenter getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            presenter = getPresenter();
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}
