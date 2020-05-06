package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.CurrentStreakModel;


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
        streak.setText(String.format("%d %s", model.getStreak(),
                view.getContext().getString(R.string.days)));
    }
}
