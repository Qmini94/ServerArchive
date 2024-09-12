package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.domain.customer.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository : CustomerRepository) : CustomerService {
    override fun createCustomer(req: CustomerRequest): CustomerResponse? {
        return try {
            customerRepository.save(req.toEntity()).toResponse()
        } catch (e: Exception) {
            throw Exception("Something went wrong while creating a customer.")
        }
    }

    override fun getAllCustomers(): List<CustomerResponse> {
        val customers = customerRepository.findAll()

        return customers.map { it.toResponse() }
    }
}