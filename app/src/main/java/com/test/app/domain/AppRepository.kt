package com.test.app.domain

import com.test.app.data.db.entity.Employee
import com.test.app.data.network.Resource

interface AppRepository {

    suspend fun employees(): Resource<MutableList<Employee?>?>
}