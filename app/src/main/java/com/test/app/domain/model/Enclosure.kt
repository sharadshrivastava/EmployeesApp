package com.test.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Enclosure(
    val link: String?,
    val thumbnail: String?,
    val type: String?
): Parcelable