package com.example.strile.infrastructure.progress;

import com.example.strile.data_firebase.models.User;
import com.example.strile.utilities.extensions.DateUtilities;

import java.util.Date;

public class Progress {

    public static User addExperience(int experience, User user) {
        int totalExp = experience + user.getExperience();
        int goalExp = user.getGoalExperience();
        if (totalExp < goalExp) {
            user.setExperience(Math.max(totalExp, 0));
        } else {
            int level = user.getLevel();
            while (totalExp > goalExp) {
                level++;
                totalExp -= goalExp;
                goalExp = goalExp * 3 / 2;
            }
            user.setExperience(totalExp);
            user.setGoalExperience(goalExp);
            user.setLevel(level);
        }
        long date = user.getDateLastActiveDay();
        long currentDate = DateUtilities.getDateOfDayWithoutTime(new Date()).getTime();
        if (date < currentDate) {
            user.setDateLastActiveDay(currentDate);
        }

        return user;
    }
}
