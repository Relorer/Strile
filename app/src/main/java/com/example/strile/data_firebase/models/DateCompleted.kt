package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DateCompleted(val date: Long, var isComplete: Boolean) : Parcelable {
}