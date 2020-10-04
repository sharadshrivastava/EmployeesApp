package com.test.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.data.AppRepositoryImpl
import com.test.app.data.network.ToDoApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.rules.TestRule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    var mockWebServer = MockWebServer()

    val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("").toString())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ToDoApi::class.java)

    var repository =  AppRepositoryImpl(api)

    fun setResponse(fileName: String) {
        val input = this.javaClass.classLoader?.getResourceAsStream(fileName)
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody(input?.bufferedReader().use { it!!.readText() })
        )
    }

    fun setErrorResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
    }
}
