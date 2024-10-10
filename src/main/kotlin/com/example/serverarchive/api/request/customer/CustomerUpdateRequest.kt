package com.example.serverarchive.api.request.customer

import com.example.serverarchive.domain.customer.entity.Customer

data class CustomerUpdateRequest(
    val companyName: String,
    val serverIdx: String,
    val serviceIdx: String,
    val managers: String,
    val codeType: Int,
    val memo: String? = null,
    val registrantId: String
) {
    fun updateEntity(existingCustomer: Customer): Customer {
        return existingCustomer.apply {
            this.name = this@CustomerUpdateRequest.companyName
            this.serverIdx = this@CustomerUpdateRequest.serverIdx
            this.serviceIdx = this@CustomerUpdateRequest.serviceIdx
            this.managers = this@CustomerUpdateRequest.managers
            this.codeType = this@CustomerUpdateRequest.codeType
            this.memo = this@CustomerUpdateRequest.memo
            this.regId = this@CustomerUpdateRequest.registrantId
            this.updatedDate = java.time.LocalDateTime.now()
        }
    }
}
