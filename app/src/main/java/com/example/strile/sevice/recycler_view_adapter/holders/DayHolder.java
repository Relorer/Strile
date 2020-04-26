package com.example.strile.sevice.recycler_view_adapter.holders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.sevice.event_handler_interfaces.OnClickListener;
import com.example.strile.sevice.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.sevice.recycler_view_adapter.models.DayModel;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

public class DayHolder extends BaseHolder<DayModel> {

    private TextView textWeekday;
    private TextView textNumber;
    private ImageView imageCircle;

    public DayHolder(@NonNull View itemView, OnModelChangedListener<DayModel> onModelChangedListener,
                     final OnClickListener<DayModel> onClickListener) {
        super(itemView, onModelChangedListener);
        textWeekday = view.findViewById(R.id.text_weekday);
        textNumber = view.findViewById(R.id.text_number);
        imageCircle = view.findViewById(R.id.image_accent_circle);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.onClick(model);
                }
            }
        });
    }

    @Override
    protected void _bind() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(model.getDay());
        textWeekday.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()).toUpperCase());
        textNumber.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        if (model.isSelected()) {
            imageCircle.setVisibility(View.VISIBLE);
            textNumber.setTextColor(view.getContext().getColor(R.color.colorPrimary));
        }
        else {
            imageCircle.setVisibility(View.INVISIBLE);
            textNumber.setTextColor(view.getContext().getColor(R.color.colorBlack));
        }
    }
}
