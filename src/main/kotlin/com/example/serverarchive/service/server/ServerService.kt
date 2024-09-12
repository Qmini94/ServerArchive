package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.response.server.ServerResponse

interface ServerService {
    fun createServer(req: ServerRequest): ServerResponse? {
        TODO("Not yet implemented")
    }
}