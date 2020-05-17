package com.example.strile.sevice.recycler_view_adapter.models;

public class InfoProgressModel extends BaseModel {

    private long level;
    private long experience;
    private long remained;

    public InfoProgressModel(boolean topMargin, long level, long experience, long remained) {
        super(topMargin);
        this.level = level;
        this.experience = experience;
        this.remained = remained;
    }

    public InfoProgressModel(int id, boolean topMargin, long level, long experience, long remained) {
        super(id, topMargin);
        this.level = level;
        this.experience = experience;
        this.remained = remained;
    }

    @Override
    public int getType() {
        return INFO_PROGRESS_TYPE;
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
