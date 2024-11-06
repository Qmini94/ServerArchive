package com.example.serverarchive.api.response.service

import com.example.serverarchive.domain.service.entity.Service
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Schema(description = "서비스정보")
class ServiceResponse(
    val idx: Int? = null,
    @Schema(description = "도메인 URL", nullable = false, required = true)
    val domainUrl: String,
    @Schema(description = "도메인 등록일", nullable = false, required = true)
    val domainResister: String,
    @Schema(description = "도메인 구매처", nullable = true, required = false)
    val certificateIssuer: String,
    @Schema(description = "인증서 갱신일", nullable = true, required = false)
    val certificateRenewalDate: String? = null,
    @Schema(description = "웹메일 총 갯수", nullable = true, required = false)
    val webmailCnt: Int? = null,
    @Schema(description = "웹메일 계정", nullable = true, required = false)
    val webmailAccount: String? = null,
    @Schema(description = "생성일", nullable = true, required = false)
    val createdDate: String? = null,
    @Schema(description = "업데이트 날짜", nullable = true, required = false)
    val updatedDate: String? = null,
    val regId: String? = null,
    val memo: String? = null
) {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        fun Service.toResponse(): ServiceResponse {
            return ServiceResponse(
                idx = this.idx,
                domainUrl = this.domainUrl,
                domainResister = this.domainResister,
                certificateIssuer = this.certificateIssuer,
                certificateRenewalDate = this.certificateRenewalDate,
                webmailCnt = this.webmailCnt,
                webmailAccount = this.webmailAccount,
                memo = this.memo,
                createdDate = this.createdDate?.format(formatter),
                updatedDate = this.updatedDate?.format(formatter)
            )
        }
    }
}
