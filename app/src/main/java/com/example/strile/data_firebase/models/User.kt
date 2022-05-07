package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    override var id: String = "",
    var tasks: HashMap<String,Task> = HashMap(),
    var habits: HashMap<String,Habit> = HashMap(),
    var executed: HashMap<String,Executed> = HashMap(),
    var level: Int = 0,
    var experience: Int = 0,
    var goalExperience: Int = 100,
    var dateLastActiveDay: Long = 0
) : Parcelable, IModel {
}
