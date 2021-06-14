package com.test.app.domain.usecases

import android.content.Context
import com.test.app.EmployeeApp
import com.test.app.R
import com.test.app.data.AppRepositoryImpl
import com.test.app.data.network.Resource
import com.test.app.domain.model.ApiResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EmployeesUseCase @Inject constructor(
    private val repository: AppRepositoryImpl,
    @ApplicationContext val context: Context
) {
    //Sorting logic is added in use case/domain layer because its a business rule.

    //If whole list needs to be deleted for invalid data.
    suspend fun employees(): Resource<ApiResponse?> {
        val employeeResource = repository.employees()
        employeeResource.data?.employees?.forEach {
            if (it?.isValid() == false) {
                return Resource.error(context.getString(R.string.malformed) ?: "", null)
            }
        }
        employeeResource.data?.employees?.sortBy {
            it?.full_name
        }
        return employeeResource
    }
}