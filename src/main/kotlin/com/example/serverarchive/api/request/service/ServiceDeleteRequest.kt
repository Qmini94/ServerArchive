package com.example.serverarchive.api.request.service

import com.example.serverarchive.domain.service.entity.Service
import com.example.serverarchive.util.ErrorCodes
import io.swagger.v3.oas.annotations.media.Schema

data class ServiceDeleteRequest(
    @Schema(description = "고유값", nullable = false, required = true)
    val idx: Int,
    @Schema(description = "도메인 URL", nullable = false, required = true)
    val domainUrl: String,
    @Schema(description = "도메인 등록일", nullable = false, required = true)
    val domainResister: String,
    @Schema(description = "도메인 구매처 ", nullable = true, required = true)
    var certificateIssuer: String,
    @Schema(description = "인증서 갱신일", nullable = true, required = false)
    val certificateRenewalDate: String,
    @Schema(description = "웹메일 계정 갯수", nullable = true, required = false)
    val webmailCnt: Int,
    @Schema(description = "웹메일 계정", nullable = true, required = false)
    val webmailAccount: String? = null,
    @Schema(description = "메모", nullable = true, required = false)
    val memo: String? = null,
) {
    fun validate(): Boolean {
        requireNotNull(this.domainUrl) { ErrorCodes.getMessage(0) }
        requireNotNull(this.domainResister) { ErrorCodes.getMessage(0) }
        return true
    }

    fun toEntity(): Service {

        return Service(
            idx = this.idx,
            domainUrl = this.domainUrl,
            domainResister = this.domainResister,
            certificateIssuer = this.certificateIssuer,
            certificateRenewalDate = this.certificateRenewalDate,
            webmailCnt = this.webmailCnt,
            webmailAccount = this.webmailAccount,
            memo = this.memo,
        )

    }
}