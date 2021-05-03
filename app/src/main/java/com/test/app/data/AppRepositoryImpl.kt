package com.test.app.data

import com.test.app.data.db.EmployeesDao
import com.test.app.data.db.entity.Employee
import com.test.app.data.network.EmployeesApi
import com.test.app.data.network.Resource
import com.test.app.data.network.ResponseHandler
import com.test.app.domain.AppRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val api: EmployeesApi,
    private val dao: EmployeesDao
) : AppRepository {

    override fun employees() = dao.employees()

    override suspend fun remoteEmployees(): Resource<List<Employee?>?> = try {
        val employeeResponse = api.employees()
        val list = mutableListOf<Employee?>()

        employeeResponse?.employees?.forEach {
            list.add(it?.toEmployee())
        }
        dao.insertEmployees(list)
        ResponseHandler.handleSuccess(list)
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}