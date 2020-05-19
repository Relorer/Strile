package com.example.strile.sevice.recycler_view_adapter.items.graph_progress;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;
import com.example.strile.views.GraphProgressCanvas;

public class GraphProgressHolder extends BaseHolder<GraphProgressModel> {

    private final Spinner spinnerCaseType;
    private final GraphProgressCanvas graphProgressCanvas;

    public GraphProgressHolder(@NonNull View itemView,
                               @NonNull OnModelChangedListener<GraphProgressModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        spinnerCaseType = view.findViewById(R.id.spinner_case_type);
        graphProgressCanvas = view.findViewById(R.id.frame_graph);
        spinnerCaseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setGraphParams(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setGraphParams(0);
            }
        });
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                itemView.getContext(),
                R.array.case_type,
                R.layout.spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerCaseType.setAdapter(adapter);
    }

    @Override
    public void bind(GraphProgressModel model) {
        super.bind(model);
        final int pos = spinnerCaseType.getSelectedItemPosition();
        setGraphParams(pos);
    }

    private void setGraphParams(int position) {
        if (position % 2 == 0) {
            graphProgressCanvas.setMax(model.getMaxHabit());
            graphProgressCanvas.setPoints(model.getHabitsByDays());
        }
        else {
            graphProgressCanvas.setMax(model.getMaxTask());
            graphProgressCanvas.setPoints(model.getTasksByDays());
        }
    }
}
