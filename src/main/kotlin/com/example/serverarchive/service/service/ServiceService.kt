package com.example.serverarchive.service.service

import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.response.service.ServiceResponse
import org.springframework.stereotype.Service

interface ServiceService {
    fun createService(req: ServiceRequest): ServiceResponse?
}