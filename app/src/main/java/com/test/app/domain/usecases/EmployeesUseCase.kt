package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import javax.inject.Inject

class EmployeesUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    suspend fun employees() = repository.employees()
}