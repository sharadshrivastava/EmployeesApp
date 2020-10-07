package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import javax.inject.Inject

class EmployeesUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    //Sorting logic is added in usecase/domain layer because its a business rule.
    suspend fun employees() = repository.employees().apply {
        data?.employees?.sortBy {
            it?.full_name
        }
    }
}