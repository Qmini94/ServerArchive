package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.ServerResponse.Companion.toResponse
import com.example.serverarchive.domain.server.repository.ServerRepository
import org.springframework.stereotype.Service

@Service
class ServerServiceImpl(private val serverRepository: ServerRepository) : ServerService {
    override fun createServer(req: ServerRequest): ServerResponse? {
        if (req.validate()) {
            val serverToSave = req.toEntity()
            return serverRepository.save(serverToSave).toResponse()
        } else {
            return null
        }
    }
}