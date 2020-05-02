package com.example.strile.database.entities;

import androidx.room.Entity;

import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

@Entity(tableName = "complete_case")
public class CompleteCaseModel extends BaseModel {

    private String name;
    private long dateComplete;
    private int experience;
    private String typeCase;

    public CompleteCaseModel(String name, long dateComplete, int experience, String typeCase) {
        this.name = name;
        this.dateComplete = dateComplete;
        this.experience = experience;
        this.typeCase = typeCase;
    }

    @Override
    public int getType() {
        return COMPLETE_CASE_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(long dateComplete) {
        this.dateComplete = dateComplete;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getTypeCase() {
        return typeCase;
    }

    public void setTypeCase(String typeCase) {
        this.typeCase = typeCase;
    }
}
