package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.database.entities.TaskModel;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskHolder extends CaseHolder {

    public TaskHolder(@NonNull View itemView,
                      final OnModelChangedListener<CaseModel> onModelChangedListener,
                      final OnClickListener<CaseModel> onClickCaseListener) {
        super(itemView, onModelChangedListener, onClickCaseListener);
        done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isBinding())
                model.setState(isChecked);
            changeStateDone();
            if (onModelChangedListener != null)
                onModelChangedListener.onChanged(model);
        });
        itemView.setOnClickListener(v -> {
            if (onClickCaseListener != null)
                onClickCaseListener.onClick(model);
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        TaskModel taskModel = (TaskModel) model;
        if (new Date().getTime() >= taskModel.getDeadline() && taskModel.isDeadline())
            info.setTextColor(view.getContext().getColor(R.color.colorRed));
        else
            info.setTextColor(view.getContext().getColor(R.color.colorLightGray));
        if (!taskModel.isDeadline()) info.setText("No deadline");
        else {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(taskModel.getDeadline());
            info.setText(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
                    + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR));
        }
    }
}
