package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.request.server.UpdateServerRequest
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.UpdateServerResponse

interface ServerService {
    fun createServer(req: ServerRequest): ServerResponse? {
        TODO("Not yet implemented")
    }
    fun getAllServers(): List<ServerResponse>
    fun deleteServer(id: Long): Boolean
    fun findById(id: Long): ServerResponse?
    fun updateServer(id: Long, req: UpdateServerRequest): UpdateServerResponse?
}