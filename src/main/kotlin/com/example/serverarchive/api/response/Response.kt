package com.example.serverarchive.api.response

data class SingleResponse<T>(
    val result: ResponseCode? = ResponseCode.SUCCESS,
    val message: String? = null,
    var data: T? = null
)

data class MultiResponse<T>(
    val result: ResponseCode? = ResponseCode.SUCCESS,
    val message: String? = null,
    var data: List<T>? = null
)

enum class ResponseCode {
    SUCCESS, ERROR
}