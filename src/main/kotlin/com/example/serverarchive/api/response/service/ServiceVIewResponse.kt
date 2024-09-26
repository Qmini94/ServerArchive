package com.example.serverarchive.api.response.service

import io.swagger.v3.oas.annotations.media.Schema

data class ServiceVIewResponse (
    @Schema(description = "도메인 URL", nullable = false, required = true)
    val domainUrl: String,
    @Schema(description = "도메인 등록일", nullable = false, required = true)
    val domainResister: String,
    @Schema(description = "도메인 구매처 ", nullable = true, required = true)
    var certificateIssuer: String? = null,
    @Schema(description = "인증서 갱신일", nullable = true, required = false)
    val certificateRenewalDate: String? = null,
    @Schema(description = "웹메일 계정 갯수", nullable = true, required = false)
    val webmailCnt: Int? = null,
    @Schema(description = "웹메일 계정", nullable = true, required = false)
    val webmailAccount: String? = null,
    @Schema(description = "메모", nullable = true, required = false)
    val memo: String? = null,
){
}