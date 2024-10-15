package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerCreateRequest
import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.domain.customer.repository.CustomerRepository
import com.example.serverarchive.util.ErrorCode
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun getCustomerList(pageable: Pageable): Page<CustomerResponse> {
        return try {
            customerRepository.findAll(pageable)
                .map { customer -> customer.toResponse() }
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
    }

    override fun getCustomerById(idx: Int): CustomerResponse {
        return customerRepository.findById(idx).map { it.toResponse() }
            .orElseThrow {throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)}
    }

    override fun createCustomer(req: CustomerCreateRequest): CustomerResponse {
        return try {
            customerRepository.save(req.toEntity()).toResponse()
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
    }

    override fun updateCustomer(idx: Int, req: CustomerUpdateRequest): CustomerResponse? {
        return try {
            val existingCustomer = customerRepository.findById(idx).orElseThrow {
                throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)
            }
            val updatedCustomer = req.updateEntity(existingCustomer)

            customerRepository.save(updatedCustomer).toResponse()
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
    }

    override fun deleteCustomerById(idx: Int): CustomerResponse {
        val customer = customerRepository.findById(idx)
            .orElseThrow {throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)}
        customerRepository.deleteById(idx)

        return customer.toResponse()
    }
}
