package com.example.serverarchive.service.server

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.request.server.ServerUpdateRequest
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.ServerResponse.Companion.toResponse
import com.example.serverarchive.domain.server.entity.Server
import com.example.serverarchive.domain.server.entity.ServerSearchOption
import com.example.serverarchive.domain.server.repository.ServerRepository
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.SearchUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ServerServiceImpl(private val serverRepository: ServerRepository) : ServerService {
    override fun findById(idx: Long): ServerResponse? {
        val serverEntity = serverRepository.findById(idx).orElse(null)
        return serverEntity?.toResponse()
    }

    override fun getAllServers(pageable: Pageable, searchParams: Map<String, String?>): Page<ServerResponse> {
        return try {
            val searchKey = searchParams["searchKey"]?.takeIf { it.isNotBlank() }
            val searchOptions = searchParams["selectedOption"]?.split(",") ?: emptyList()

            if (searchKey != null && !SearchUtils.isValidSearchKey(searchKey)) {
                throw IllegalArgumentException("Invalid search key")
            }

            val searchCriteria = searchKey?.let {
                SearchUtils.mapSearchParams(
                    searchKey = it,
                    searchOptions = searchOptions,
                    options = ServerSearchOption.values(),
                    fieldNameSelector = { it.fieldName }
                )
            } ?: emptyMap()

            val startDate = searchParams["startDate"]?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }
            val endDate = searchParams["endDate"]?.takeIf { it.isNotBlank() }?.let { LocalDateTime.parse(it) }

            val specification: Specification<Server> = SearchUtils.createSpecification(
                searchParams = searchCriteria,
                startDate = startDate,
                endDate = endDate
            )

            val servers = serverRepository.findAll(specification, pageable)
            servers.map { server -> server.toResponse() }
        } catch (e: Exception) {
            throw IllegalArgumentException(ErrorCode.UNKNOWN_ERROR.name, e)
        }
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

