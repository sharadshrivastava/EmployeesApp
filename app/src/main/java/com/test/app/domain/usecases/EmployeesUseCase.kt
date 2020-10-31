package com.test.app.domain.usecases

import com.test.app.data.AppRepositoryImpl
import com.test.app.data.db.entity.Employee
import com.test.app.data.network.Resource
import javax.inject.Inject

class EmployeesUseCase @Inject constructor(private val repository: AppRepositoryImpl) {
    //Sorting logic is added in use case/domain layer because its a business rule.

    //If whole list needs to be deleted for invalid data.
    suspend fun employees(): Resource<MutableList<Employee?>?> {
        val employeeResource = repository.employees()
        employeeResource.data?.sortBy {
            it?.fullName
        }
        return employeeResource
    }
}