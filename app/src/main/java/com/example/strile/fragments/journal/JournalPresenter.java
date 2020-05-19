package com.example.strile.fragments.journal;

import com.example.strile.fragments.journal.cases.JournalCasesPage;
import com.example.strile.fragments.journal.cases.habits.JournalHabitsFragment;
import com.example.strile.fragments.journal.cases.tasks.JournalTasksFragment;
import com.example.strile.sevice.date.Day;
import com.example.strile.sevice.presenter.BasePresenter;
import com.example.strile.sevice.recycler_view_adapter.items.BaseModel;
import com.example.strile.sevice.recycler_view_adapter.items.day.DayModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JournalPresenter extends BasePresenter<JournalFragment> {

    private final List<BaseModel> days;
    private DayModel selected;

    JournalPresenter() {
        days = new ArrayList<>();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(Day.getDateOfDayWithoutTime(new Date()));
        selected = new DayModel(false, calendar.getTime(), true);
        days.add(selected);
        for (int i = 1; i < 30; i++){
            calendar.add(Calendar.DATE, -1);
            days.add(new DayModel(false, calendar.getTime(), false));
        }
    }

    @Override
    protected void updateView() {
        view().setSortedListDays(days);
    }

    void addButtonClicked(JournalCasesPage page) {
        view().dismissSnackbar();
        if (page instanceof JournalHabitsFragment) view().startAddHabitFragment();
        else if (page instanceof JournalTasksFragment) view().startAddTaskFragment();
    }

    void dayClicked(DayModel day) {
        if (day.getId() != selected.getId()) {
            int posBefore = days.indexOf(selected);
            int posAfter = days.indexOf(day);
            if (posBefore >= 0) {
                DayModel model = new DayModel(selected.getId(), selected.isTopMargin(), selected.getDate(), false);
                days.set(posBefore, model);
            }
            if (posAfter >= 0) {
                selected = new DayModel(day.getId(), day.isTopMargin(), day.getDate(), true);
                days.set(posAfter, selected);
            }
            view().setVisibleDayOnPages(day.getDate());
            updateView();
        }
    }
}