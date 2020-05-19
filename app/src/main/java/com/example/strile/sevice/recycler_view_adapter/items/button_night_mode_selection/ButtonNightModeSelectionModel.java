package com.example.strile.sevice.recycler_view_adapter.items.button_night_mode_selection;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;

public class ButtonNightModeSelectionModel extends BaseModel {

    private int nightMode;

    public ButtonNightModeSelectionModel(boolean topMargin, int nightMode) {
        super(topMargin);
        this.nightMode = nightMode;
    }

    public ButtonNightModeSelectionModel(int id, boolean topMargin, int nightMode) {
        super(id, topMargin);
        this.nightMode = nightMode;
    }

    @AppCompatDelegate.NightMode
    public int getNightMode() {
        return nightMode;
    }

    public ButtonNightModeSelectionModel setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        this.nightMode = nightMode;
        return this;
    }

    @Override
    public int getType() {
        return BUTTON_NIGHT_MODE_SELECTION_TYPE;
    }
}
