package com.test.app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.app.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeesDao {

    @Query("SELECT * FROM Employee")
    fun employees(): Flow<MutableList<Employee?>>

    @Query("SELECT count(*) FROM Employee")
    suspend fun entryCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmployees(employees: List<Employee?>)
}