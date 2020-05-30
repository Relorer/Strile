package com.example.strile.service.recycler_view_adapter.items.switch_setting;

import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseHolder;

public class SwitchSettingHolder extends BaseHolder<SwitchSettingModel> {

    private final TextView name;
    private final ImageView icon;
    private final Switch _switch;

    protected SwitchSettingHolder(@NonNull View itemView,
                                  @NonNull OnModelChangedListener<SwitchSettingModel> onModelChangedListener) {
        super(itemView, onModelChangedListener);

        name = itemView.findViewById(R.id.text_name);
        icon = itemView.findViewById(R.id.image_icon_settings);
        _switch = itemView.findViewById(R.id.switch_setting);

        _switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.setChecked(isChecked);
            onModelChangedListener.onChanged(model);
        });
    }

    @Override
    public void bind(SwitchSettingModel model) {
        super.bind(model);
        name.setText(model.getName());
        icon.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(), model.getIconId(), null));
        icon.setColorFilter(itemView.getContext().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
        _switch.setChecked(model.isChecked());
    }
}
