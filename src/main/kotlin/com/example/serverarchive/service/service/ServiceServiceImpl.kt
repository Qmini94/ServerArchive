package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceUpdateRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.service.ServiceResponse
import com.example.serverarchive.api.response.service.ServiceResponse.Companion.toResponse
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.domain.service.entity.Service
import com.example.serverarchive.domain.service.entity.ServiceSearchOption
import com.example.serverarchive.domain.service.repository.ServiceRepository
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.ErrorCodes
import com.example.serverarchive.util.SearchUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

@org.springframework.stereotype.Service
class ServiceServiceImpl(private val serviceRepository: ServiceRepository) : ServiceService {
    override fun createService(req: ServiceRequest): ServiceResponse? {

        return try {
            if (serviceRepository.existsServiceByDomainUrl(req.domainUrl)) {
                throw IllegalArgumentException(ErrorCodes.getMessage(1004))
            }
            serviceRepository.save(req.toEntity()).toResponse()
        } catch (e: Exception) {
            throw Exception("Something went wrong while creating a service.")
        }
    }

    override fun getServiceList(
        pageable: Pageable,
        searchParams: Map<String, String?>
    ): Page<ServiceResponse> {
        return try {
            val searchKey = searchParams["searchKey"]?.takeIf { it.isNotBlank() }
            val searchOptions = searchParams["selectedOption"]?.split(",") ?: emptyList()
            if (searchKey != null && !SearchUtils.isValidSearchKey(searchKey)) {
                throw IllegalArgumentException("Invalid search key")
            }
            val searchCriteria = searchKey?.let {
                SearchUtils.mapSearchParams(
                    searchKey = it,
                    searchOptions = searchOptions,
                    options = ServiceSearchOption.values(),
                    fieldNameSelector = { it.fieldName }
                )
            } ?: emptyMap()
            val startDate = searchParams["startDate"]?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }
            val endDate = searchParams["endDate"]?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }

            val specification: Specification<Service> = SearchUtils.createSpecification(
                searchParams = searchCriteria,
                startDate = startDate,
                endDate = endDate
            )
            val services = serviceRepository.findAll(specification, pageable)
            services.map { service -> service.toResponse() }
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
    }

    override fun deleteServiceById(idx: Int): ServiceResponse {
        val service = serviceRepository.findById(idx)
            .orElseThrow { throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name) }
        serviceRepository.deleteById(idx)

        return service.toResponse()
    }


    override fun getServiceById(idx: Int): ServiceResponse {
        return serviceRepository.findById(idx).map { it.toResponse() }
            .orElseThrow { throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name) }
    }

    override fun updateService(idx: Int, req: ServiceUpdateRequest): ServiceResponse? {
        return try {
            val existingService = serviceRepository.findById(idx).orElseThrow {
                throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)
            }
            val updatedService = req.updateEntity(existingService)

            serviceRepository.save(updatedService).toResponse()
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
    }

    override fun viewService(req: ServiceViewRequest): ServiceVIewResponse? {

        if (serviceRepository.existsServiceByDomainUrl(req.domainUrl)) {
            throw IllegalArgumentException(ErrorCodes.getMessage(0))
        }

        return ServiceVIewResponse(
            domainUrl = req.domainUrl,
            domainResister = req.domainResister,
        )
    }

}