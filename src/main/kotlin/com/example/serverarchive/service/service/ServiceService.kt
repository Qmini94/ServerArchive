package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.request.service.ServiceDeleteRequest
import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceUpdateRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.service.ServiceDeleteResponse
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.api.response.service.ServiceResponse

interface ServiceService {
    fun createService(req: ServiceRequest): ServiceResponse?
    fun getAllServices(): List<ServiceResponse>
    fun getServiceById(idx: Int): ServiceResponse
    fun deleteServiceById(idx: Int): ServiceResponse?
    fun viewService(req: ServiceViewRequest): ServiceVIewResponse?
    fun updateService(idx: Int, customer: ServiceUpdateRequest): ServiceResponse?
}