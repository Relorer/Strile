package com.example.strile.service.recycler_view_adapter.items.switch_setting;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.strile.R;
import com.example.strile.service.event_handler_interfaces.OnModelChangedListener;
import com.example.strile.service.recycler_view_adapter.items.BaseModel;
import com.example.strile.service.recycler_view_adapter.items.BaseRenderer;

public class SwitchSettingRenderer extends BaseRenderer<SwitchSettingModel, SwitchSettingHolder> {

    public SwitchSettingRenderer(@NonNull OnModelChangedListener onModelChangedListener) {
        super(onModelChangedListener);
    }

    @NonNull
    @Override
    public SwitchSettingHolder createViewHolder(@NonNull ViewGroup parent) {
        return new SwitchSettingHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting_with_switch, parent, false),
                onModelChangedListener);
    }

    @Override
    public int getType() {
        return BaseModel.SWITCH_SETTING_TYPE;
    }
}
