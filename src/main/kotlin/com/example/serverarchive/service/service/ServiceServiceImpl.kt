package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.request.service.ServiceDeleteRequest
import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceUpdateRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.ServerResponse.Companion.toResponse
import com.example.serverarchive.api.response.service.ServiceDeleteResponse
import com.example.serverarchive.api.response.service.ServiceResponse
import com.example.serverarchive.api.response.service.ServiceResponse.Companion.toResponse
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.domain.service.repository.ServiceRepository
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.ErrorCodes
import org.springframework.stereotype.Service

@Service
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


	override fun getAllServices(): List<ServiceResponse> {
		val services = serviceRepository.findAll()
		return services.map { it.toResponse() }
	}

	override fun deleteServiceById(idx: Int): ServiceResponse {
		val service = serviceRepository.findById(idx)
				.orElseThrow {throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)}
		serviceRepository.deleteById(idx)

		return service.toResponse()
	}


	override fun getServiceById(idx: Int): ServiceResponse {
		return serviceRepository.findById(idx).map { it.toResponse() }
				.orElseThrow {throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)}
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