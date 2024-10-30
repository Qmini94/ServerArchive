package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.request.server.UpdateServerRequest
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.ServerResponse.Companion.toResponse
import com.example.serverarchive.api.response.server.UpdateServerResponse
import com.example.serverarchive.domain.server.entity.toUpdateResponse
import com.example.serverarchive.domain.server.repository.ServerRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

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

    override fun getAllServers(): List<ServerResponse> {
        val servers = serverRepository.findAll()
        return servers.map { it.toResponse() }
    }

    override fun deleteServer(idx: Long): Boolean {
        return try {
            serverRepository.deleteById(idx)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun findById(idx: Long): ServerResponse? {
        val serverEntity = serverRepository.findById(idx).orElse(null)
        return serverEntity?.toResponse()
    }

    override fun updateServer(idx: Long, req: UpdateServerRequest): UpdateServerResponse? {
        val existingServer = serverRepository.findById(idx).orElse(null) ?: return null

        existingServer.apply {
            ip = req.ip
            port = req.port
            serverUser = req.serverUser
            rootPassword = req.rootPassword
            databaseName = req.databaseName
            updatedDate = LocalDateTime.now()
        }

        return serverRepository.save(existingServer).toUpdateResponse()
    }


}

