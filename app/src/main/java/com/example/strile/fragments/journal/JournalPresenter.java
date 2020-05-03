package com.example.strile.fragments.journal;

import com.example.strile.R;
import com.example.strile.sevice.DateManager;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.models.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.models.DayModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JournalPresenter extends BasePresenter<Boolean, JournalFragment> {

    private List<BaseModel> days;
    private DayModel selected;

    JournalPresenter() {
        model = true;
        days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 30; i++){
            days.add(new DayModel(calendar.getTimeInMillis(), false));
            calendar.add(Calendar.DATE, -1);
        }
        selected = (DayModel) days.get(0);
        selected.setSelected(true);
    }

    @Override
    protected void updateView() {
        view().setSortedListDays(days);
    }

    void addButtonClicked(String nameList) {
        if (nameList != null) {
            if(nameList.equals(view().getString(R.string.habits))) view().startAddHabitFragment();
            else view().startAddTaskFragment();
        }
    }

    void dayClicked(DayModel day) {
        selected.setSelected(false);
        selected = day;
        selected.setSelected(true);
        DateManager.setVisibleDay(selected.getDay());
    }
}
