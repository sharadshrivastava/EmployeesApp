package com.test.app.domain.model

data class ApiResponse(
    val feed: Feed?,
    val items: List<Item>?,
    val status: String?
)