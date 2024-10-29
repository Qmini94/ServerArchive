package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerCreateRequest
import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.domain.customer.entity.Customer
import com.example.serverarchive.domain.customer.entity.CustomerSearchOption
import com.example.serverarchive.domain.customer.repository.CustomerRepository
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.SpecificationUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun getCustomerList(
        pageable: Pageable,
        searchParams: Map<String, String?>
    ): Page<CustomerResponse> {
        return try {
            val searchKey = searchParams["searchKey"]
            val searchOptions = searchParams["selectedOption"]?.split(",") ?: emptyList()

            val searchCriteria = if (!searchKey.isNullOrBlank()) {
                SpecificationUtils.mapSearchParams(
                    searchKey = searchKey,
                    searchOptions = searchOptions,
                    options = CustomerSearchOption.values(),
                    fieldNameSelector = { it.fieldName }
                )
            } else {
                emptyMap()
            }

            val startDate = searchParams["startDate"]?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }
            val endDate = searchParams["endDate"]?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }

            val specification: Specification<Customer> = SpecificationUtils.createSpecification(
                searchParams = searchCriteria,
                startDate = startDate,
                endDate = endDate
            )

            val customers = customerRepository.findAll(specification, pageable)
            customers.map { customer -> customer.toResponse() }
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
