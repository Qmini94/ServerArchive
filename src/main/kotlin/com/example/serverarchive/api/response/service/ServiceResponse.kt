package com.example.serverarchive.api.response.service

import com.example.serverarchive.domain.service.entity.Service
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "서비스정보")
class ServiceResponse(
    val idx: Int? = null,
    @Schema(description = "도메인 URL", nullable = false, required = true)
    val domainUrl: String,
    @Schema(description = "도메인 등록일", nullable = false, required = true)
    val domainResister: String,
    @Schema(description = "도메인 구매처", nullable = true, required = false)
    val certificateIssuer: String? = null,
    @Schema(description = "인증서 갱신일", nullable = true, required = false)
    val certificateRenewalDate: String? = null,
    @Schema(description = "웹메일 총 갯수", nullable = true, required = false)
    val webmailCnt: Int? = null,
    @Schema(description = "웹메일 계정", nullable = true, required = false)
    val webmailAccount: String? = null,
    @Schema(description = "생성일", nullable = true, required = false)
    val createdDate: LocalDateTime? = null,
    @Schema(description = "업데이트 날짜", nullable = true, required = false)
    val updatedDate: LocalDateTime? = null,
    val regId: String? = null,
    val memo: String? = null
) {
    companion object {
        fun Service.toResponse(): ServiceResponse {
            return ServiceResponse(
                domainUrl = this.domainUrl,
                domainResister = this.domainResister,
                certificateIssuer = this.certificateIssuer,
                certificateRenewalDate = this.certificateRenewalDate,
                webmailCnt = this.webmailCnt,
                webmailAccount = this.webmailAccount,
            )
        }
        fun CreatedFrom(service: Service): ServiceResponse {
            return ServiceResponse(
                domainUrl = service.domainUrl,
                domainResister = service.domainResister,
                certificateIssuer = service.certificateIssuer,
                certificateRenewalDate = service.certificateRenewalDate,
                webmailCnt = service.webmailCnt,
                webmailAccount = service.webmailAccount,
            )
        }
    }
}
