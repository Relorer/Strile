package com.example.strile.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;

@Entity(tableName = "executed")
public class Executed {

    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final long caseId;
    private final String name;
    private final long dateComplete;
    private final int experience;
    private final String typeCase;

    public Executed(long id, long caseId, String name, long dateComplete, int experience, String typeCase) {
        this.id = id;
        this.caseId = caseId;
        this.name = name;
        this.dateComplete = dateComplete;
        this.experience = experience;
        this.typeCase = typeCase;
    }

    public long getId() {
        return id;
    }

    public long getCaseId() {
        return caseId;
    }

    public String getName() {
        return name;
    }

    public long getDateComplete() {
        return dateComplete;
    }

    public int getExperience() {
        return experience;
    }

    public String getTypeCase() {
        return typeCase;
    }
}
