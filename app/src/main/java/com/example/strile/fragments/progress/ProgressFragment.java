package com.example.strile.fragments.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strile.R;
import com.example.strile.activities.settings.SettingsActivity;
import com.example.strile.service.presenter.PresenterManager;
import com.example.strile.service.recycler_view_adapter.adapters.ProgressListAdapter;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;

import java.util.List;

public class ProgressFragment extends Fragment {

    private ProgressPresenter presenter;

    private ProgressListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_progress, container, false);

        final ImageView imageSettings = view.findViewById(R.id.image_special_purpose_button_right);
        final View settingsButton = view.findViewById(R.id.special_purpose_button_right);
        final RecyclerView recycler = view.findViewById(R.id.recycler);
        final TextView progressTitle = view.findViewById(R.id.text_title);

        progressTitle.setText(R.string.progress);
        imageSettings.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings, null));
        settingsButton.setOnClickListener(v -> presenter.buttonSettingsClicked());

        adapter = new ProgressListAdapter(model -> {
        });
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        adapter.setHasStableIds(true);
        recycler.setAdapter(adapter);

        return view;
    }

    public void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
    }

    public void openSettings() {
        SettingsActivity.start(getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            presenter = new ProgressPresenter();
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