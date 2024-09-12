package com.example.serverarchive.api.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl

@Schema(description = "단일건 응답 객체", title = "SingleResponse")
data class SingleResponse<T>(
    @Schema(description = "응답 결과", nullable = false)
    val result: ResponseCode? = ResponseCode.ERROR,
    @Schema(description = "응답 메시지", nullable = false)
    val message: String? = null,
    @Schema(description = "응답 데이터", nullable = false)
    var data: T? = null
)

@Schema(description = "복수건 응답 객체", title = "MultiResponse")
data class MultiResponse<T>(
    @Schema(description = "응답 결과", nullable = false)
    val result: ResponseCode? = ResponseCode.ERROR,
    @Schema(description = "응답 메시지", nullable = false)
    val message: String? = null,
    @Schema(description = "응답 데이터", nullable = false)
    var data: List<T>? = null
)

@Schema(description = "페이징 응답 객체", title = "PageResponse")
data class PageResponse<T>(
    @Schema(description = "응답 결과", nullable = false)
    val result: ResponseCode? = ResponseCode.ERROR,
    @Schema(description = "응답 메시지", nullable = false)
    val message: String? = null,
    @Schema(description = "응답 데이터", nullable = false)
    val data: Page<T>? = PageImpl(listOf())
)

enum class ResponseCode {
    SUCCESS, ERROR
}