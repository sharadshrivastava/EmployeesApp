package com.test.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.app.data.db.entity.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeesDB : RoomDatabase() {
    abstract fun employeesDao(): EmployeesDao
}