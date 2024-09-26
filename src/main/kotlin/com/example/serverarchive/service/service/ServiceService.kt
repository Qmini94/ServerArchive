package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.service.ServiceDeleteRequest
import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.service.ServiceDeleteResponse
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.api.response.service.ServiceResponse

interface ServiceService {
    fun createService(req: ServiceRequest): ServiceResponse?
    fun deleteService(req: ServiceDeleteRequest): ServiceDeleteResponse?
    fun viewService(req: ServiceViewRequest): ServiceVIewResponse?
}