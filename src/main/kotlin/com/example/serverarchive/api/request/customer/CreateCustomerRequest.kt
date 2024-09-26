package com.example.serverarchive.api.request.customer

import com.example.serverarchive.domain.customer.entity.Customer
import java.time.LocalDateTime

data class CreateCustomerRequest(
    private val companyName: String,
    private val serverIdx: String,
    private var serviceIdx: String,
    private val managers: String,
    private val codeType: Int,
    private val memo: String? = null,
    private val registrantId: String
) {
    fun toEntity(): Customer {
        return Customer(
            name = this.companyName,
            serverIdx = this.serverIdx,
            serviceIdx = this.serviceIdx,
            managers = this.managers,
            codeType = this.codeType,
            memo = this.memo,
            updatedDate = LocalDateTime.now(),
            regId = this.registrantId,
            log = "생성되었습니다."
        )
    }
}

