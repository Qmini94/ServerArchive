package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.request.server.ServerUpdateRequest
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.ServerResponse.Companion.toResponse
import com.example.serverarchive.domain.server.repository.ServerRepository
import com.example.serverarchive.util.ErrorCode
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ServerServiceImpl(private val serverRepository: ServerRepository) : ServerService {
    override fun findById(idx: Long): ServerResponse? {
        val serverEntity = serverRepository.findById(idx).orElse(null)
        return serverEntity?.toResponse()
    }

    override fun getAllServers(): List<ServerResponse> {
        val servers = serverRepository.findAll()
            .sortedByDescending { it.idx }  // 등록순으로 내림차순  // .sortedBy:오름차순
        return servers.map { it.toResponse() }
    }

    override fun createServer(req: ServerRequest): ServerResponse? {
        val serverToSave = req.toEntity()
        return serverRepository.save(serverToSave).toResponse()
    }

    override fun updateServer(idx: Long, req: ServerUpdateRequest): ServerResponse? {
        return try {
            val existingServer = serverRepository.findById(idx).orElseThrow {
                throw IllegalArgumentException(ErrorCode.INVALID_PARAMETER.name)
            }
            val serverToUpdate = req.toUpdateEntity(existingServer)

            serverRepository.save(serverToUpdate).toResponse()
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
    }

    override fun deleteServer(idx: Long): Boolean {
        return try {
            serverRepository.deleteById(idx)
            true
        } catch (e: Exception) {
            false
        }
    }


}

