package com.example.strile.fragments.journal.cases;

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
import com.example.strile.database.entities.Case;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedCaseChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnCheckedButtonHidingChangeListener;
import com.example.strile.sevice.event_handler_interfaces.OnClickCaseListener;
import com.example.strile.sevice.presenter.PresenterManager;
import com.example.strile.sevice.recycler_view_adapter.ButtonHidingModel;
import com.example.strile.sevice.recycler_view_adapter.ItemModel;

import java.util.List;

public abstract class JournalCasesFragment extends Fragment {
    private JournalCasesPresenter presenter;

    private JournalListAdapter adapter = new JournalListAdapter();

    private RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_journal_cases, container, false);

        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.setHasStableIds(true);
        recycler.setAdapter(adapter);

        adapter.setOnClickItemListener(new OnClickCaseListener() {
            @Override
            public void onClick(Case c) {
                presenter.itemClicked(c);
            }
        });
        adapter.setOnCheckedItemChangeListener(new OnCheckedCaseChangeListener() {
            @Override
            public void onCheckedChanged(Case c, boolean isChecked) {
                presenter.itemStateChanged(c);
            }
        });
        adapter.setOnCheckedButtonHidingChangeListener(new OnCheckedButtonHidingChangeListener() {
            @Override
            public void onCheckedChanged(ButtonHidingModel button, boolean isChecked) {
                presenter.buttonHidingStateChanged(button);
            }
        });


        return view;
    }

    void setSortedList(@NonNull List<ItemModel> items) {
        adapter.setItems(items);
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
