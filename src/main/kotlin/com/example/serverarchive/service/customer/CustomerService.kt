package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerRequest
import com.example.serverarchive.api.response.customer.CustomerResponse

interface CustomerService {
    fun createCustomer(customer: CustomerRequest): Boolean
    fun getAllCustomers(): List<CustomerResponse>
}