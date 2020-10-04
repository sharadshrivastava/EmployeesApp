package com.test.app.data.network

import com.test.app.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ToDoApi {

    @GET(FEED_PATH)
    suspend fun newsFeed(@Query(FEED_QUERY_PARAM) query: String): ApiResponse?

    companion object {
        const val BASE_URL = "https://api.rss2json.com/"
        private const val FEED_PATH = "v1/api.json"
        private const val FEED_QUERY_PARAM = "rss_url"
        const val FEED_QUERY_VALUE = "http://www.abc.net.au/news/feed/51120/rss.xml"
    }
}