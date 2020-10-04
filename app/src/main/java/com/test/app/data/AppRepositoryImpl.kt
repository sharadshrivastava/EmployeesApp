package com.test.app.data

import com.test.app.data.network.Resource
import com.test.app.data.network.ResponseHandler
import com.test.app.data.network.ToDoApi
import com.test.app.data.network.ToDoApi.Companion.FEED_QUERY_VALUE
import com.test.app.domain.AppRepository
import com.test.app.domain.model.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(private val api: ToDoApi) : AppRepository {

    override suspend fun newsFeeds(): Resource<ApiResponse?> = try {
        ResponseHandler.handleSuccess(api.newsFeed(FEED_QUERY_VALUE))
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}