package com.example.strile.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "executed")
public class Executed {

    private final long caseId;
    private final String name;
    private final long dateComplete;
    private final int experience;
    private final String typeCase;
    @PrimaryKey(autoGenerate = true)
    private long id;

    public Executed(long caseId, String name, long dateComplete, int experience, String typeCase) {
        this.caseId = caseId;
        this.name = name;
        this.dateComplete = dateComplete;
        this.experience = experience;
        this.typeCase = typeCase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
