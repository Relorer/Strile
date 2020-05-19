package com.example.strile.sevice.recycler_view_adapter.items.day;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.items.BaseHolder;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class DayHolder extends BaseHolder<DayModel> {

    private final TextView textWeekday;
    private final TextView textNumber;
    private final ImageView imageCircle;

    public DayHolder(@NonNull View itemView,
                     @NonNull OnModelChangedListener<DayModel> onModelChangedListener,
                     @NonNull final OnClickListener<DayModel> onClickListener) {
        super(itemView, onModelChangedListener);
        textWeekday = view.findViewById(R.id.text_weekday);
        textNumber = view.findViewById(R.id.text_number);
        imageCircle = view.findViewById(R.id.image_accent_circle);
        view.setOnClickListener(v -> onClickListener.onClick(model));
    }

    @Override
    public void bind(DayModel model) {
        super.bind(model);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(model.getDate());
        textWeekday.setText(Objects.requireNonNull(
                calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()),
                "calendar return null")
                .toUpperCase());
        textNumber.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        if (model.isSelected()) {
            imageCircle.setVisibility(View.VISIBLE);
            textNumber.setTextColor(view.getContext().getColor(R.color.colorTextActiveDay));
        } else {
            imageCircle.setVisibility(View.INVISIBLE);
            textNumber.setTextColor(view.getContext().getColor(R.color.colorBlack));
        }
    }
}
