package com.test.app

import com.test.app.domain.model.ApiResponse
import com.test.app.domain.model.Employee

private val list = ArrayList<Employee?>()
val response = ApiResponse(list).apply {
    val e1 = Employee(
        null, null, null, "First Name",
        null, null, null, "First Team", "123"
    )

    val e2 = Employee(
        null, null, null, "Second Name",
        null, null, null, null, "234"
    )

    val e3 = Employee(
        null,
        null,
        null,
        null,
        null,
        null,
        "https://s3.amazonaws.com/sq-mobile-interview/photos/f8fc3c8e-b8ed-41d8-9005-537cf657c169/small.jpg",
        null,
        null
    )

    val e4: Employee? = null

    list.add(e1)
    list.add(e2)
    list.add(e3)
    list.add(e4)
}