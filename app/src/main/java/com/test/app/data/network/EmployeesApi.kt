package com.test.app.data.network

import com.test.app.domain.model.ApiResponse
import retrofit2.http.GET

interface EmployeesApi {

    @GET(EMPLOYEES_PATH)
    suspend fun employees(): ApiResponse?

    companion object {
        const val BASE_URL = "https://s3.amazonaws.com/"
        private const val EMPLOYEES_PATH = "sq-mobile-interview/employees.json" //sq-mobile-interview/employees_malformed.json
    }
}