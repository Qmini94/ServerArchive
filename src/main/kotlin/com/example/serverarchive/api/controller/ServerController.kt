package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.server.ServerRequest
import com.example.serverarchive.api.request.server.UpdateServerRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.server.ServerResponse
import com.example.serverarchive.api.response.server.UpdateServerResponse
import com.example.serverarchive.service.server.ServerServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/server")
@Tag(name = "Server", description = "서버 정보 등록 API")
class ServerController(private val serverService: ServerServiceImpl) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "서버 정보 등록", description = "서버 정보를 등록합니다.")
    fun createServer(@RequestBody req: ServerRequest): SingleResponse<ServerResponse?> {
        var result = ResponseCode.ERROR
        var message = "Request Failed"

        val response = serverService.createServer(req)
        response?.let {
            result = ResponseCode.SUCCESS
            message = "Successfully created server info"
        }

        return SingleResponse(
            result = result,
            message = message,
            data = response
        )
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "서버 정보 수정", description = "서버 정보를 수정합니다.")
    fun updateServer(
        @PathVariable id: Long,
        @RequestBody req: UpdateServerRequest
    ): SingleResponse<UpdateServerResponse?> {
        var result = ResponseCode.ERROR
        var message = "Request Failed"

        val updatedServer = serverService.updateServer(id, req)
        updatedServer?.let {
            result = ResponseCode.SUCCESS
            message = "Successfully updated server info"
        }

        return SingleResponse(
            result = result,
            message = message,
            data = updatedServer
        )
    }

}