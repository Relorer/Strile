package com.example.strile.infrastructure.rvadapter.items.button_repeat;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.strile.R;
import com.example.strile.ui.dialog.DialogRepeatOptions;
import com.example.strile.utilities.events.OnModelChangedListener;
import com.example.strile.infrastructure.rvadapter.items.BaseHolder;

import java.text.DateFormatSymbols;
import java.util.Locale;

public class ButtonRepeatHolder extends BaseHolder<ButtonRepeatModel> {

    private final TextView text;

    public ButtonRepeatHolder(@NonNull View itemView,
                              @NonNull final OnModelChangedListener<ButtonRepeatModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);
        text = view.findViewById(R.id.text_name);
        ImageView icon = itemView.findViewById(R.id.image_icon);
        view.setOnClickListener(v -> openDialogRepeatOptions(model.getDaysRepeat()));

        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), R.drawable.repeat, null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void bind(ButtonRepeatModel model) {
        super.bind(model);
        changeTextRepeat();
    }

    private void changeTextRepeat() {
        text.setTextColor(view.getContext().getColor(R.color.colorRed));
        for (boolean day : model.getDaysRepeat())
            if (day) text.setTextColor(view.getContext().getColor(R.color.colorBlack));
        text.setText(fromArrayDaysToString(model.getDaysRepeat()));
    }

    private String fromArrayDaysToString(boolean[] days) {
        final DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());

        final String[] weekdays = new String[7];
        System.arraycopy(symbols.getShortWeekdays(), 1, weekdays, 0, 7);

        int count = 0;
        for (boolean d : days) count += d ? 1 : 0;
        String text = view.getContext().getString(R.string.repeat) + " ";
        if (count == 7) text += view.getContext().getString(R.string.everyday);
        else if (count == 0) text = view.getContext().getString(R.string.no_repeat);
        else {
            if (days[0]) text += weekdays[0] + ", ";
            if (days[1]) text += weekdays[1] + ", ";
            if (days[2]) text += weekdays[2] + ", ";
            if (days[3]) text += weekdays[3] + ", ";
            if (days[4]) text += weekdays[4] + ", ";
            if (days[5]) text += weekdays[5] + ", ";
            if (days[6]) text += weekdays[6] + ", ";
            text = text.substring(0, text.length() - 2);
        }
        return text;
    }

    private void openDialogRepeatOptions(boolean[] checkedDays) {
        final FragmentManager manager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
        final DialogRepeatOptions dialog = new DialogRepeatOptions(checkedDays, result -> {
            onModelChangedListener.onChanged(model.setDaysRepeat(result));
            changeTextRepeat();
        });
        dialog.show(manager, "Repeat");
    }
}
