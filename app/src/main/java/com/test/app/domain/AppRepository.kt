package com.test.app.domain

import com.test.app.data.network.Resource
import com.test.app.domain.model.ApiResponse

interface AppRepository {

    suspend fun newsFeeds(): Resource<ApiResponse?>
}