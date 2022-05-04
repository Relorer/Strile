package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DateCompleted(var date: Long = 0, var isComplete: Boolean = false) : Parcelable {
}