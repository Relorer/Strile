package com.example.strile.data_firebase.models

import android.os.Parcelable
import com.example.strile.infrastructure.settings.Difficulty
import com.example.strile.utilities.extensions.DateUtilities
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Habit(
    var id: String?,
    var name: String? = "",
    var difficulty: Int = Difficulty.maxDifficulty / 3,
    private var goalTime: Long = 0,
    private var elapsedTime: Long = 0,
    var daysRepeat: Int = arrayRepeatToInt(booleanArrayOf(true, true, true, true, true, true, true)),
    private val datesCompleted: MutableList<DateCompleted?> = ArrayList(),
    var notificationTime: Long = -1
) : Parcelable {

    fun plannedForDay(date: Date?): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val weekday = calendar[Calendar.DAY_OF_WEEK] - 1
        return (daysRepeat and Math.pow(2.0, weekday.toDouble())
            .toInt()).toDouble() == Math.pow(2.0, weekday.toDouble())
    }

    fun isCompleteOnDay(date: Date): Boolean {
        return getDateCompleted(date).isComplete
    }

    fun getGoalTime(): Long {
        return goalTime
    }

    fun setGoalTime(goalTime: Long) {
        val day = DateUtilities.getDateOfDayWithoutTime(Date())
        if (elapsedTime < goalTime && isCompleteOnDay(day)) {
            setStateForDay(false, day)
        } else if (elapsedTime >= goalTime && !isCompleteOnDay(day) && goalTime > 0) {
            setStateForDay(true, day)
        }
        this.goalTime = goalTime
    }

    fun getElapsedTime(): Long {
        return elapsedTime
    }

    fun setElapsedTime(elapsedTime: Long) {
        val day = DateUtilities.getDateOfDayWithoutTime(Date())
        if (elapsedTime >= goalTime && !isCompleteOnDay(day) && goalTime > 0) {
            setStateForDay(true, day)
        }
        this.elapsedTime = elapsedTime
    }

    fun setDaysRepeat(daysRepeat: BooleanArray) {
        this.daysRepeat = arrayRepeatToInt(daysRepeat)
    }

    fun getDatesCompleted(): List<DateCompleted?> {
        return datesCompleted
    }

    fun getStreakByDay(date: Date): Int {
        var date = date
        date = DateUtilities.getDateOfDayWithoutTime(date)
        val calendar = Calendar.getInstance()
        calendar.time = date
        datesCompleted.sortWith(Comparator { o1: DateCompleted?, o2: DateCompleted? -> if (o2!!.date - o1!!.date < 0) -1 else 1 })
        var streak = 0
        for (item in datesCompleted) {
            val itemDate = item!!.date
            if (itemDate <= date.time) {
                while (calendar.timeInMillis != itemDate) {
                    calendar.add(Calendar.DATE, -1)
                    if (calendar.timeInMillis == itemDate) break
                    if (plannedForDay(calendar.time)) return streak
                }
                if (item.isComplete) streak++ else if (itemDate != date.time && plannedForDay(
                        Date(
                            itemDate
                        )
                    )
                ) break
            }
        }
        return streak
    }

    val daysRepeatAsArray: BooleanArray
        get() {
            val days = BooleanArray(7)
            for (i in days.indices) {
                days[i] =
                    (daysRepeat and Math.pow(2.0, i.toDouble()).toInt()).toDouble() == Math.pow(
                        2.0,
                        i.toDouble()
                    )
            }
            return days
        }

    fun setStateForDay(state: Boolean, date: Date) {
        val dateCompleted = getDateCompleted(date)
        dateCompleted.isComplete = state
    }

    private fun getDateCompleted(date: Date): DateCompleted {
        val dateOfDayWithoutTime = DateUtilities.getDateOfDayWithoutTime(date)
        val found = datesCompleted.stream()
            .filter { item: DateCompleted? -> dateOfDayWithoutTime.time == item!!.date }
            .findAny()
            .orElse(null)
        if (found == null) {
            val newDateCompleted = DateCompleted(dateOfDayWithoutTime.time, false)
            datesCompleted.add(newDateCompleted)
            if (dateOfDayWithoutTime.time == DateUtilities.getDateOfDayWithoutTime(Date()).time) elapsedTime =
                0
            return newDateCompleted
        }
        return found
    }

    companion object {
        fun arrayRepeatToInt(daysRepeat: BooleanArray): Int {
            var repeat = 0
            for (i in daysRepeat.indices) {
                if (daysRepeat[i]) repeat += Math.pow(2.0, i.toDouble()).toInt()
            }
            return repeat
        }
    }
}