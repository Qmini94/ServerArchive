package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceUpdateRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.api.response.service.ServiceResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ServiceService {
    fun getServiceList(pageable: Pageable, searchParams: Map<String, String?>): Page<ServiceResponse>
    fun createService(req: ServiceRequest): ServiceResponse?
    fun getServiceById(idx: Int): ServiceResponse
    fun deleteServiceById(idx: Int): ServiceResponse?
    fun viewService(req: ServiceViewRequest): ServiceVIewResponse?
    fun updateService(idx: Int, service: ServiceUpdateRequest): ServiceResponse?
}