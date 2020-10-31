package com.test.app.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @ColumnInfo(name = "full_name") val fullName: String?,
    @ColumnInfo(name = "photo_url_small") val imgUrl: String?,
    @ColumnInfo(name = "team") val team: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)