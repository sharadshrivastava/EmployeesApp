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

    override suspend fun employees(): Resource<MutableList<Employee?>?> = try {

        if (dao.entryCount() == 0) {
            val employeeResponse = api.employees()
            val list = mutableListOf<Employee?>()

            employeeResponse?.employees?.forEach {
                val employee = Employee(it?.full_name, it?.photo_url_small, it?.team)
                list.add(employee)
            }
            dao.insertEmployees(list)
            ResponseHandler.handleSuccess(list)
        } else {
            var list: MutableList<Employee?>? = null
            dao.employees().first {
                list = it
                return@first true
            }
            ResponseHandler.handleSuccess(list)
        }

    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}