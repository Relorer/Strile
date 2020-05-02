package com.example.strile.fragments.progress;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.strile.R;
import com.example.strile.fragments.timer.TimerPresenter;
import com.example.strile.sevice.presenter.PresenterManager;
import com.example.strile.sevice.recycler_view_adapter.adapters.ProgressListAdapter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

import java.util.List;

import static com.example.strile.sevice.DifficultyManager.getColor;

public class ProgressFragment extends Fragment {

    private ProgressPresenter presenter;

    private ProgressListAdapter adapter = new ProgressListAdapter();

    private View buttonSettings;
    private ImageView imageSettings;
    private RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        imageSettings = view.findViewById(R.id.image_special_purpose_button_right);
        ((TextView) view.findViewById(R.id.text_title)).setText("Progress");
        recycler = view.findViewById(R.id.recycler);

        imageSettings.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.settings, null));

        recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        adapter.setHasStableIds(true);
        recycler.setAdapter(adapter);

        return view;
    }

    void setSortedList(@NonNull List<BaseModel> items) {
        adapter.setItems(items);
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