package com.test.app.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["full_name", "team"], unique = true)])
data class Employee(
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "photo_url_small") val imgUrl: String?,
    @ColumnInfo(name = "team") val team: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable