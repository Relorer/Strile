package com.example.strile.database.entities;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.strile.sevice.recycler_view_adapter.ItemModel;

public abstract class Case extends ItemModel implements Parcelable {

    public Case() {
        super();
    }

    public abstract void setState(boolean complete);
    public abstract boolean plannedForToday();
    public abstract boolean completed();

    public abstract int getId();
    public abstract void setId(int id);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract int getDifficulty();
    public abstract void setDifficulty(int difficulty);
}
