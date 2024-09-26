package com.example.serverarchive.api.request.customer

import com.example.serverarchive.domain.customer.entity.Customer

data class UpdateCustomerRequest(
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
            this.name = this@UpdateCustomerRequest.companyName
            this.serverIdx = this@UpdateCustomerRequest.serverIdx
            this.serviceIdx = this@UpdateCustomerRequest.serviceIdx
            this.managers = this@UpdateCustomerRequest.managers
            this.codeType = this@UpdateCustomerRequest.codeType
            this.memo = this@UpdateCustomerRequest.memo
            this.regId = this@UpdateCustomerRequest.registrantId
            this.updatedDate = java.time.LocalDateTime.now()
        }
    }
}
