package com.test.app.db

import android.content.Context
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.test.app.data.db.EmployeesDB
import com.test.app.data.db.EmployeesDao
import com.test.app.data.db.entity.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DBTest {

    lateinit var employeesDao: EmployeesDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        employeesDao = Room.inMemoryDatabaseBuilder(
            context, EmployeesDB::class.java
        ).build().employeesDao()

        runBlocking {
            val list = listOf(Employee("Sharad", "abc.png", "Android"))
            employeesDao.insertEmployees(list)
        }
    }


    @Test
    fun testInsert() {
        runBlocking {
            withContext(Dispatchers.Main) {
                employeesDao.employees().asLiveData().observeForever {
                    Assert.assertEquals("Sharad", it[0]?.fullName)
                }
            }
        }
    }

    @Test
    fun testDBEntries() {
        runBlocking {
            val count = employeesDao.entryCount()
            Assert.assertTrue(count == 1)
        }
    }
}