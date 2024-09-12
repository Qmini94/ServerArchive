package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.response.service.ServiceResponse
import com.example.serverarchive.domain.service.repository.ServiceRepository
import com.example.serverarchive.util.ErrorCodes
import org.springframework.stereotype.Service

@Service
class ServiceServiceImpl(private val serviceRepository: ServiceRepository) : ServiceService {
	override fun createService(req: ServiceRequest): ServiceResponse? {

		if (serviceRepository.existsServiceByDomainUrl(req.domainUrl)) {
			throw IllegalArgumentException(ErrorCodes.getMessage(1004))
		}

		return ServiceResponse(
			domainUrl = req.domainUrl,
			domainResister = req.domainResister,
		)
	}
}