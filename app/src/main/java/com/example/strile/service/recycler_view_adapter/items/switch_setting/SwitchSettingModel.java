package com.example.strile.service.recycler_view_adapter.items.switch_setting;

import androidx.annotation.DrawableRes;

import com.example.strile.service.recycler_view_adapter.items.BaseModel;

public class SwitchSettingModel extends BaseModel {

    private final String name;
    private final int iconId;
    private boolean checked;

    public SwitchSettingModel(boolean topMargin, String name, @DrawableRes int iconId, boolean checked) {
        super(topMargin);
        this.name = name;
        this.iconId = iconId;
        this.checked = checked;
    }

    public SwitchSettingModel(int id, boolean topMargin, String name, @DrawableRes int iconId, boolean checked) {
        super(id, topMargin);
        this.name = name;
        this.iconId = iconId;
        this.checked = checked;
    }

    @Override
    public int getType() {
        return SWITCH_SETTING_TYPE;
    }

    public String getName() {
        return name;
    }

    @DrawableRes
    public int getIconId() {
        return iconId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
