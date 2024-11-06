package com.example.serverarchive.api.request.service

import com.example.serverarchive.domain.service.entity.Service

data class ServiceUpdateRequest (
        val domainUrl: String,
        val domainResister: String,
        val certificateIssuer: String,
        val certificateRenewalDate: String,
        val webmailCnt: Int,
        val webmailAccount: String,
        val memo: String? = null,
){
    fun updateEntity(existingService: Service): Service {
        return existingService.apply {
            this.domainUrl = this@ServiceUpdateRequest.domainUrl
            this.domainResister = this@ServiceUpdateRequest.domainResister
            this.certificateIssuer = this@ServiceUpdateRequest.certificateIssuer
            this.certificateRenewalDate = this@ServiceUpdateRequest.certificateRenewalDate
            this.webmailCnt = this@ServiceUpdateRequest.webmailCnt
            this.webmailAccount = this@ServiceUpdateRequest.webmailAccount
            this.memo = this@ServiceUpdateRequest.memo
        }
    }
}

