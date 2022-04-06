package com.example.strile.infrastructure.rvadapter.items.button_time_goal;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.ui.dialog.DialogTimeGoalOptions;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;

public class ButtonTimeGoalHolder extends BaseHolder<ButtonTimeGoalModel> {

    private final TextView text;

    public ButtonTimeGoalHolder(@NonNull View itemView,
                                @NonNull final OnModelChangedListener<ButtonTimeGoalModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        ImageView icon = itemView.findViewById(R.id.image_icon);
        text = view.findViewById(R.id.text_name);

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.time_goal, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);

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
                        view.getResources().getQuantityString(R.plurals.minutes_without_number, goalMinutes)));
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
