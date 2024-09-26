package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CreateCustomerRequest
import com.example.serverarchive.api.request.customer.UpdateCustomerRequest
import com.example.serverarchive.api.response.customer.CustomerResponse

interface CustomerService {
    fun getAllCustomers(): List<CustomerResponse>
    fun getCustomerById(idx: Int): CustomerResponse
    fun createCustomer(customer: CreateCustomerRequest): CustomerResponse?
    fun updateCustomer(idx: Int, customer: UpdateCustomerRequest): CustomerResponse?
    fun deleteCustomerById(idx: Int): CustomerResponse?
}