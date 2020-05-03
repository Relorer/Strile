package com.example.strile.sevice;

import android.graphics.PorterDuff;

import com.example.strile.App;
import com.example.strile.R;

public class DifficultyManager {

    public static final int maxDifficult = 100;

    public static int getColor(int difficulty) {
        if (difficulty > maxDifficult / 4 * 3) return R.color.colorRed;
        else if (difficulty > maxDifficult / 2) return R.color.colorHardCase;
        else if (difficulty > maxDifficult / 4) return R.color.colorMediumCase;
        else return R.color.colorAccent;
    }

    public static String getName(int difficulty) {
        if (difficulty > maxDifficult / 4 * 3) return App.getInstance().getString(R.string.very_hard);
        else if (difficulty > maxDifficult / 2) return App.getInstance().getString(R.string.hard);
        else if (difficulty > maxDifficult / 4) return App.getInstance().getString(R.string.normal);
        else return App.getInstance().getString(R.string.simple);
    }

}