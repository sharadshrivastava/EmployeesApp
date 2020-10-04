package com.test.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val author: String?,
    val categories: List<String>?,
    val content: String?,
    val description: String?,
    val enclosure: Enclosure?,
    val guid: String?,
    val link: String?,
    val pubDate: String?,
    val thumbnail: String?,
    val title: String?
): Parcelable