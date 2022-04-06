package com.example.strile.infrastructure.rvadapter.items.current_streak;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;


public class CurrentStreakHolder extends BaseHolder<CurrentStreakModel> {

    private final TextView streak;

    public CurrentStreakHolder(@NonNull View itemView,
                               @NonNull OnModelChangedListener<CurrentStreakModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        streak = view.findViewById(R.id.text_streak);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bind(CurrentStreakModel model) {
        super.bind(model);
        int countDays = model.getStreak();
        streak.setText(view.getContext().getResources().getQuantityString(R.plurals.days, countDays, countDays));
    }
}
