package com.example.strile.sevice.recycler_view_adapter.models;

public class InfoProgressModel extends BaseModel {

    private String name;
    private long experience;
    private long remained;
    private long level;
    private long days;

    @Override
    public int getType() {
        return INFO_PROGRESS_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyOfChanges();
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
        notifyOfChanges();
    }

    public long getRemained() {
        return remained;
    }

    public void setRemained(long remained) {
        this.remained = remained;
        notifyOfChanges();
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
        notifyOfChanges();
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
        notifyOfChanges();
    }
}
