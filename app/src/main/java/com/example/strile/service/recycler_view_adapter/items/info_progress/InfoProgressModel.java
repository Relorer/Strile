package com.example.strile.service.recycler_view_adapter.items.info_progress;

import com.example.strile.service.recycler_view_adapter.items.BaseModel;

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

    public InfoProgressModel setExperience(long experience) {
        this.experience = experience;
        return this;
    }

    public long getRemained() {
        return remained;
    }

    public InfoProgressModel setRemained(long remained) {
        this.remained = remained;
        return this;
    }

    public long getLevel() {
        return level;
    }

    public InfoProgressModel setLevel(long level) {
        this.level = level;
        return this;
    }
}
