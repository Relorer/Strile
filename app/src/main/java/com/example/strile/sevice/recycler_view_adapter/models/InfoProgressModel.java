package com.example.strile.sevice.recycler_view_adapter.models;

public class InfoProgressModel extends BaseModel {

    private final String name;
    private final long level;
    private final long experience;
    private final long remained;
    private final long days;

    public InfoProgressModel(boolean topMargin, String name, long level, long experience, long remained, long days) {
        super(topMargin);
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.remained = remained;
        this.days = days;
    }

    @Override
    public int getType() {
        return INFO_PROGRESS_TYPE;
    }

    public String getName() {
        return name;
    }

    public long getExperience() {
        return experience;
    }

    public long getRemained() {
        return remained;
    }

    public long getLevel() {
        return level;
    }

    public long getDays() {
        return days;
    }
}
