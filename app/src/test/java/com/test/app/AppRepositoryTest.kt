package com.test.app

import com.test.app.data.network.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest : BaseTest() {

    @Test
    fun testSuccessResponse() {
        setResponse("response.json")
        runBlocking {
            Assert.assertTrue(
                repository.newsFeeds().status == Resource.Status.SUCCESS
            )
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue(
                repository.newsFeeds().status != Resource.Status.SUCCESS
            )
        }
    }

    @Test
    fun testNewsItems() {
        setResponse("response.json")
        runBlocking {
            val expectedItems = 10 //in local json file, we have 10 news items.
            Assert.assertTrue(
                repository.newsFeeds().data?.items?.size == expectedItems
            )
        }
    }

    //In this way we can test other functionality as well using mock webserver and dummy response.
}