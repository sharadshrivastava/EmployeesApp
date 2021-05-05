package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import com.test.app.data.db.entity.Employee
import com.test.app.data.network.Resource
import javax.inject.Inject

class EmployeesUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    suspend fun remoteEmployees() = repository.remoteEmployees()

    fun employees() = repository.employees()

    suspend fun isDBEmpty() = repository.isDBEmpty()

}