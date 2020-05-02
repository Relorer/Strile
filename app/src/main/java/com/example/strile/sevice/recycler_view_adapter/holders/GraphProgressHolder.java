package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.GraphProgressModel;
import com.example.strile.views.GraphProgressCanvas;

public class GraphProgressHolder extends BaseHolder<GraphProgressModel> {

    private Spinner spinnerCaseType;
    private GraphProgressCanvas graphProgressCanvas;

    public GraphProgressHolder(@NonNull View itemView, OnModelChangedListener<GraphProgressModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);

        spinnerCaseType = view.findViewById(R.id.spinner_case_type);
        graphProgressCanvas = view.findViewById(R.id.frame_graph);

        spinnerCaseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position % 2 == 0) {
                    graphProgressCanvas.setMax(model.getMaxHabit());
                    graphProgressCanvas.setPoints(model.getHabitsByDays());
                }
                else {
                    graphProgressCanvas.setMax(model.getMaxTask());
                    graphProgressCanvas.setPoints(model.getTasksByDays());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                graphProgressCanvas.setMax(model.getMaxHabit());
                graphProgressCanvas.setPoints(model.getHabitsByDays());
            }
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        int pos = spinnerCaseType.getSelectedItemPosition();
        if (pos % 2 == 0) {
            graphProgressCanvas.setMax(model.getMaxHabit());
            graphProgressCanvas.setPoints(model.getHabitsByDays());
        }
        else {
            graphProgressCanvas.setMax(model.getMaxTask());
            graphProgressCanvas.setPoints(model.getTasksByDays());
        }
    }
}