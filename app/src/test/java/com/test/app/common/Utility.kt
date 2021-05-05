package com.test.app.common

import com.google.gson.Gson
import com.test.app.BaseTest
import com.test.app.data.db.entity.Employee
import com.test.app.domain.model.ApiResponse
import java.io.InputStreamReader

fun readJson(fileName: String): ApiResponse {
    val input = BaseTest::class.java.classLoader?.getResourceAsStream(fileName)
    val reader = InputStreamReader(input)
    return Gson().fromJson(reader, ApiResponse::class.java)
}

fun employees(apiResponse: ApiResponse): List<Employee> {
    val list = mutableListOf<Employee>()
    apiResponse.employees?.forEach {
        list.add(it?.toEmployee()!!)
    }
    return  list.toList()
}