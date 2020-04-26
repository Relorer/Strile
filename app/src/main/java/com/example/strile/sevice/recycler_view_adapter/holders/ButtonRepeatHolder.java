package com.example.strile.sevice.recycler_view_adapter.holders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.sevice.dialog.DialogRepeatOptions;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.call_back_interfaces.CompleteDialogRepeatCallback;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.ButtonRepeatModel;

public class ButtonRepeatHolder extends BaseHolder<ButtonRepeatModel> {

    private View view;
    private TextView text;

    public ButtonRepeatHolder(@NonNull View itemView, final OnModelChangedListener<ButtonRepeatModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        view = itemView;
        text = view.findViewById(R.id.text_repeat);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogRepeatOptions(model.getDaysRepeatArray());
            }
        });
    }

    @Override
    protected void _bind() {
        super._bind();
        changeTextRepeat();
    }

    private void changeTextRepeat() {
        text.setTextColor(view.getContext().getColor(R.color.colorRed));
        for (boolean d : model.getDaysRepeatArray()) if (d)
            text.setTextColor(view.getContext().getColor(R.color.colorBlack));
        text.setText(DateManager.fromArrayDaysToString(model.getDaysRepeatArray()));
    }

    private void openDialogRepeatOptions(boolean[] checkedDays) {
        boolean[] checked = new boolean[7];
        System.arraycopy(checkedDays, 0, checked, 0, 7);
        FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
        DialogRepeatOptions dialog = new DialogRepeatOptions(checked, checkedDays1 -> {
            model.setDaysRepeatArray(checked);
            changeTextRepeat();
            if (onModelChangedListener != null)
                onModelChangedListener.onChanged(model);
        });
        dialog.show(manager, "Repeat");
    }
}
