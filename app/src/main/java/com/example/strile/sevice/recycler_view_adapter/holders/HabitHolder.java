package com.example.strile.sevice.recycler_view_adapter.holders;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.database.entities.CaseModel;
import com.example.strile.database.entities.HabitModel;
import com.example.strile.sevice.call_back_interfaces.CompleteCallback;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;

public class HabitHolder extends CaseHolder {

    public HabitHolder(@NonNull View itemView,
                       final OnModelChangedListener<CaseModel> onModelChangedListener,
                       final OnClickListener<CaseModel> onClickCaseListener) {
        super(itemView, onModelChangedListener, onClickCaseListener);
        done.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isBinding()) {
                model.setState(isChecked);
                if (onModelChangedListener != null) {
                    onModelChangedListener.onChanged(model);
                }
            }
        });
        itemView.setOnClickListener(v -> {
            if (onClickCaseListener != null)
                onClickCaseListener.onClick(model);
        });

    }

    @Override
    protected void _bind() {
        super._bind();
        info.setText(((HabitModel) model).getStreak() + " day streak");
        if (((HabitModel) model).getGoalTimeSeconds() == 0) special.setImageDrawable(null);
        else
            special.setImageDrawable(ResourcesCompat.getDrawable(view.getResources(), R.drawable.time_goal, null));
    }

}