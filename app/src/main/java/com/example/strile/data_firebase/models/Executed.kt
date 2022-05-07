package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Executed(
    override var id: String = "",
    var caseId: String = "",
    var name: String = "",
    var dateComplete: Long = 0,
    var experience: Int = 0,
    var typeCase: String = ""
) : Parcelable, IModel