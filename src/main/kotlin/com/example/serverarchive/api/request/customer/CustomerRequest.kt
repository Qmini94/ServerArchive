package com.example.serverarchive.api.request.customer

import com.example.serverarchive.domain.customer.entity.Customer
import com.example.serverarchive.util.ErrorCodes
import java.time.LocalDateTime

class CustomerRequest(
    val companyName: String,
    val serverIdx: String,
    var serviceIdx: String,
    val managers: String,
    val codeType: Int,
    val memo: String? = null,
    val registrantId: String
) {
    fun validate(): Boolean {
        require(this.companyName.isNotBlank()) { ErrorCodes.getMessage(1001) }
        require(this.serverIdx.isNotBlank()) { ErrorCodes.getMessage(1002) }
        require(this.serviceIdx.isNotBlank()) { ErrorCodes.getMessage(1003) }
        require(this.managers.isNotBlank()) { ErrorCodes.getMessage(1004) }
        require(this.registrantId.isNotBlank()) { ErrorCodes.getMessage(1006) }
        return true
    }

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
