package com.example.strile.sevice;

import android.content.Context;

import com.example.strile.R;

public class Difficulty {
    public static final int maxDifficulty = 100;

    private final int value;

    public Difficulty(int value) {
        this.value = value;
    }

    public int getColor() {
        if (value > maxDifficulty / 4 * 3) return R.color.colorRed;
        else if (value > maxDifficulty / 2) return R.color.colorHardCase;
        else if (value > maxDifficulty / 4) return R.color.colorMediumCase;
        else return R.color.colorAccent;
    }

    public String getName(Context context) {
        if (value > maxDifficulty / 4 * 3) return context.getString(R.string.very_hard);
        else if (value > maxDifficulty / 2) return context.getString(R.string.hard);
        else if (value > maxDifficulty / 4) return context.getString(R.string.normal);
        else return context.getString(R.string.simple);
    }
}