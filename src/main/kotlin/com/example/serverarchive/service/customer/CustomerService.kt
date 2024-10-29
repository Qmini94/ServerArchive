package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerCreateRequest
import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomerService {
    fun getCustomerList(pageable: Pageable, searchParams: Map<String, String?>): Page<CustomerResponse>
    fun getCustomerById(idx: Int): CustomerResponse
    fun createCustomer(customer: CustomerCreateRequest): CustomerResponse?
    fun updateCustomer(idx: Int, customer: CustomerUpdateRequest): CustomerResponse?
    fun deleteCustomerById(idx: Int): CustomerResponse?
}