package com.example.strile.sevice.recycler_view_adapter.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.sevice.dialog.DialogTimeGoalOptions;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;

public class ButtonTimeGoalHolder extends BaseHolder<ButtonTimeGoalModel> {

    private final TextView text;

    public ButtonTimeGoalHolder(@NonNull View itemView,
                                @NonNull final OnModelChangedListener<ButtonTimeGoalModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        text = view.findViewById(R.id.text_goal);
        view.setOnClickListener(v -> openDialogTimeGoalOptions(model.getGoalTime()));
    }

    @Override
    public void bind(ButtonTimeGoalModel model) {
        super.bind(model);
        changeTextGoalTime();
    }

    @SuppressLint("DefaultLocale")
    private void changeTextGoalTime() {
        final int goalMinutes = (int) (model.getGoalTime() / 60 / 1000);
        switch (goalMinutes) {
            case 0:
                text.setText(R.string.no_time_goal);
                break;
            case 60:
                text.setText(String.format("%s 1 %s",
                        view.getContext().getString(R.string.for_time),
                        view.getContext().getString(R.string.hour)));
                break;
            default:
                text.setText(String.format("%s %d %s",
                        view.getContext().getString(R.string.for_time), goalMinutes,
                        view.getContext().getString(R.string.minutes)));
                break;
        }
    }

    private void openDialogTimeGoalOptions(long time) {
        final FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
        final DialogTimeGoalOptions dialog = new DialogTimeGoalOptions(time, result -> {
                onModelChangedListener.onChanged(model.setGoalTime(result));
            changeTextGoalTime();
        });
        dialog.show(manager, "TimeGoal");
    }
}
