package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subtask(val name: String, val isComplete: Boolean): Parcelable {

    fun setState(state: Boolean): Subtask {
        return Subtask(name, state)
    }
}