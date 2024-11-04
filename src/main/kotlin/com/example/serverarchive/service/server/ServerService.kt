package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.request.server.ServerUpdateRequest
import com.example.serverarchive.api.response.server.ServerResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ServerService {
    fun findById(id: Long): ServerResponse?
    fun getAllServers(pageable: Pageable, searchParams: Map<String, String?>): Page<ServerResponse>
    fun createServer(req: ServerRequest): ServerResponse?
    fun updateServer(id: Long, req: ServerUpdateRequest): ServerResponse?
    fun deleteServer(id: Long): Boolean


}