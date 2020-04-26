package com.example.strile.sevice.recycler_view_adapter.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.sevice.dialog.DialogTimeGoalOptions;
import com.example.strile.sevice.call_back_interfaces.CompleteDialogTimeGoalCallback;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonTimeGoalModel;

public class ButtonTimeGoalHolder extends BaseHolder<ButtonTimeGoalModel> {

    private View view;
    private TextView text;

    public ButtonTimeGoalHolder(@NonNull View itemView, final OnModelChangedListener<ButtonTimeGoalModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        view = itemView;
        text = view.findViewById(R.id.text_goal);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogTimeGoalOptions(model.getGoalTimeSeconds());
            }
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        changeTextGoalTime();
    }

    private void changeTextGoalTime() {
        int goalMinutes = model.getGoalTimeSeconds() / 60;
        switch (goalMinutes) {
            case 0:
                text.setText("No time goal");
                break;
            case 60:
                text.setText("For 1 hour");
                break;
            default:
                text.setText("For " + goalMinutes + " minutes");
                break;
        }
    }

    void openDialogTimeGoalOptions(int timeSeconds) {
        FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
        DialogTimeGoalOptions dialog = new DialogTimeGoalOptions(timeSeconds, new CompleteDialogTimeGoalCallback() {
            @Override
            public void onComplete(int timeSeconds) {
                model.setGoalTimeSeconds(timeSeconds);
                changeTextGoalTime();
                if (onModelChangedListener != null)
                    onModelChangedListener.onChanged(model);
            }
        });
        dialog.show(manager, "TimeGoal");
    }
}
