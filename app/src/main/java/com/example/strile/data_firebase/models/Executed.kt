package com.example.strile.data_firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Executed(
    var id: String?,
    val caseId: Long,
    val name: String,
    val dateComplete: Long,
    val experience: Int,
    val typeCase: String
) : Parcelable {

}