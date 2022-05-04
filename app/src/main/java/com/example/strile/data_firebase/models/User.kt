package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    override var id: String = "",
    var Tasks: List<Task> = ArrayList(),
    var Habits: List<Habit> = ArrayList(),
    var Executeds: List<Executed> = ArrayList()
) : Parcelable, IModel {
    constructor(Uid: String) : this(Uid, ArrayList(),ArrayList(),ArrayList()) {

    }
}
