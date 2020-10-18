package com.example.tandemtest.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    val firstName: String?,
    val pictureUrl: String?,
    val learns: List<String?>?,
    val referenceCnt: Int?,
    val natives: List<String?>?,
    val topic: String?
) : Parcelable