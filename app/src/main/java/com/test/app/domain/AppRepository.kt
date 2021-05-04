package com.test.app.domain

import com.test.app.data.db.entity.Employee
import com.test.app.data.network.Resource
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun employees(): Flow<List<Employee?>>

    suspend fun remoteEmployees(): Resource<List<Employee?>?>
}