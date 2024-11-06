package com.example.serverarchive.api.request.service

import com.example.serverarchive.domain.service.entity.Service
import com.example.serverarchive.util.ErrorCodes
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "서비스 정보")
data class ServiceRequest(
        @Schema(description = "도메인 URL", nullable = false, required = true)
        var domainUrl: String,
        @Schema(description = "도메인 등록일", nullable = false, required = true)
        var domainResister: String,
        @Schema(description = "도메인 구매처 ", nullable = true, required = true)
        var certificateIssuer: String,
        @Schema(description = "인증서 갱신일", nullable = true, required = false)
        var certificateRenewalDate: String,
        @Schema(description = "웹메일 계정 갯수", nullable = true, required = false)
        var webmailCnt: Int,
        @Schema(description = "웹메일 계정", nullable = true, required = false)
        var webmailAccount: String? = null,
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