package com.example.strile.data_firebase.models

import android.os.Parcelable
import com.example.strile.infrastructure.settings.Difficulty
import com.example.strile.utilities.extensions.DateUtilities
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Task(
    var id: String?,
    var name: String? = "",
    var difficulty: Int = Difficulty.maxDifficulty / 3,
    var description: String? = "",
    var dateCreate: Long = DateUtilities.getDateOfDayWithoutTime(Date()).time,
    var deadline: Long = 0,
    var dateComplete: Long = 0,
    var subtasks: List<Subtask>
) : Parcelable {

    fun plannedForDay(date: Date): Boolean {
        return dateCreate <= date.time && (dateComplete == 0L || dateComplete >= date.time)
    }

    fun isCompleteOnDay(date: Date): Boolean {
        return dateComplete == date.time
    }

    fun setDeadlineFromDate(deadline: Date) {
        this.deadline = deadline.time
    }

    fun setStateForDay(state: Boolean, date: Date) {
        dateComplete = 0
        if (state) dateComplete = date.time
    }

    override fun describeContents(): Int {
        return 0
    }
}