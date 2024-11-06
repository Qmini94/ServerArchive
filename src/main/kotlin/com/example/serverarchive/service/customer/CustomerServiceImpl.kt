package com.example.serverarchive.service.customer

import com.example.serverarchive.api.request.customer.CustomerCreateRequest
import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.domain.customer.entity.Customer
import com.example.serverarchive.domain.customer.entity.CustomerSearchOption
import com.example.serverarchive.domain.customer.repository.CustomerRepository
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.PaginationUtil
import com.example.serverarchive.util.SearchUtils
import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.ServerRequest
import java.time.LocalDateTime

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun getCustomerList(req: ServerRequest): Page<CustomerResponse> {
        return try {
            val pageable = PaginationUtil.parseParams(req)
            val searchKey = req.param("searchKey").orElse(null)?.takeIf { it.isNotBlank() }
            val searchOptions = req.param("selectedOption").orElse(null)?.split(",")

            if (searchKey != null && !SearchUtils.isValidSearchKey(searchKey)) {
                throw IllegalArgumentException("Invalid search key")
            }

            val searchCriteria = searchKey?.let {
                SearchUtils.mapSearchParams(
                    searchKey = it,
                    searchOptions = searchOptions ?: emptyList(),
                    options = CustomerSearchOption.values(),
                    fieldNameSelector = { it.fieldName }
                )
            } ?: emptyMap()

            val startDate = req.param("startDate").orElse(null)?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }
            val endDate = req.param("endDate").orElse(null)?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }

            val specification: Specification<Customer> = SearchUtils.createSpecification(
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
