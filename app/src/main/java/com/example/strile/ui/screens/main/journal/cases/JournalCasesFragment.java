package com.example.strile.ui.screens.main.journal.cases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.infrastructure.presenter.PresenterManager;
import com.example.strile.infrastructure.rvadapter.adapters.JournalListAdapter;
import com.example.strile.infrastructure.rvadapter.items.BaseModel;
import com.example.strile.infrastructure.rvadapter.items.CaseModel;
import com.example.strile.infrastructure.rvadapter.items.button_hiding.ButtonHidingModel;

import java.util.Date;
import java.util.List;

public abstract class JournalCasesFragment extends Fragment {

    private final JournalListAdapter adapter;
    private JournalCasesPresenter presenter;

    public JournalCasesFragment() {
        adapter = new JournalListAdapter(sender -> {
            if (sender instanceof CaseModel)
                presenter.itemClicked((CaseModel) sender);
        }, model -> {
            if (model instanceof CaseModel) {
                presenter.itemStateChanged((CaseModel) model);
            } else if (model instanceof ButtonHidingModel) {
                presenter.buttonHidingStateChanged((ButtonHidingModel) model);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        RecyclerView recycler = view.findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.setHasStableIds(true);
        recycler.setAdapter(adapter);

        return view;
    }

    public void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
    }

    public void setVisibleDay(Date day) {
        if (presenter != null)
            presenter.setVisibleDay(day);
    }

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(presenter, outState);
    }
}
