package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.domain.customer.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository : CustomerRepository) : CustomerService {
    override fun createCustomer(req: CustomerRequest): Boolean {
        return try {
            customerRepository.save(req.toEntity())
            true  // 업체가 성공적으로 생성된 경우
        } catch (e: Exception) {
            false // 예외 발생 시 false 반환
        }
    }

    override fun getAllCustomers(): List<CustomerResponse> {
        val customers = customerRepository.findAll()

        return customers.map { it.toResponse() }
    }
}