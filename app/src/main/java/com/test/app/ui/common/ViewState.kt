package com.test.app.ui.common

data class ViewState(
    val loading: Boolean = false,
    val empty: Boolean = false,
    val success: Boolean = false,
    val error: Boolean = false,
    val errorMsg: String? = null
)