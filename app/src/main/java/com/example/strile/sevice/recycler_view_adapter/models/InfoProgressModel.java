package com.example.strile.sevice.recycler_view_adapter.models;

public class InfoProgressModel extends BaseModel {

    private final String name;
    private final long days;
    private long level;
    private long experience;
    private long remained;

    public InfoProgressModel(boolean topMargin, String name, long level, long experience, long remained, long days) {
        super(topMargin);
        this.name = name;
        this.level = level;
        this.experience = experience;
        this.remained = remained;
        this.days = days;
    }

    public InfoProgressModel(int id, boolean topMargin, String name, long days, long level, long experience, long remained) {
        super(id, topMargin);
        this.name = name;
        this.days = days;
        this.level = level;
        this.experience = experience;
        this.remained = remained;
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

    public InfoProgressModel setLevel(long level) {
        this.level = level;
        return this;
    }

    public InfoProgressModel setExperience(long experience) {
        this.experience = experience;
        return this;
    }

    public InfoProgressModel setRemained(long remained) {
        this.remained = remained;
        return this;
    }
}
