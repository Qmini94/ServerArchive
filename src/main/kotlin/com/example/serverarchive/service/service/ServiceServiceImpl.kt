package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.service.ServiceDeleteRequest
import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.customer.CustomerResponse.Companion.toResponse
import com.example.serverarchive.api.response.service.ServiceDeleteResponse
import com.example.serverarchive.api.response.service.ServiceResponse
import com.example.serverarchive.api.response.service.ServiceResponse.Companion.toResponse
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.domain.service.repository.ServiceRepository
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

	override fun deleteService(req: ServiceDeleteRequest): ServiceDeleteResponse? {

		if (serviceRepository.deleteServiceByIdx(req.idx)) {
			throw IllegalArgumentException(ErrorCodes.getMessage(0))
		}

		return ServiceDeleteResponse(
			domainUrl = req.domainUrl,
			domainResister = req.domainResister,
		)
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