package com.vicki.weatherappliation.api


 data class ResponseData<T>(
    val status: Boolean? = null,
    val message: String? = null,
    val data: T? = null
)