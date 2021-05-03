package com.test.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee(
    val biography: String?,
    val email_address: String?,
    val employee_type: String?,
    val full_name: String?,
    val phone_number: String?,
    val photo_url_large: String?,
    val photo_url_small: String?,
    val team: String?,
    val uuid: String?
):Parcelable {
    fun isValid() =
        uuid != null && full_name != null && email_address != null
                && team != null && employee_type != null
}