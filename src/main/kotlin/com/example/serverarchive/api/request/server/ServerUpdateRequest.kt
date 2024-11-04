package com.example.serverarchive.api.request.server

import com.example.serverarchive.domain.server.entity.Server
import java.time.LocalDateTime

data class ServerUpdateRequest(
    val ip: String,
    val port: String,
    val rootPassword: String,
    val serverUser: String,
    val password: String,
    val databaseName: String,
    val memo: String
) {
    fun toUpdateEntity(existingServer: Server): Server {
        return existingServer.apply {
            this.ip = this@ServerUpdateRequest.ip
            this.port = this@ServerUpdateRequest.port
            this.rootPassword = this@ServerUpdateRequest.rootPassword
            this.serverUser = this@ServerUpdateRequest.serverUser
            this.password = this@ServerUpdateRequest.password
            this.databaseName = this@ServerUpdateRequest.databaseName
            this.memo = this@ServerUpdateRequest.memo
            this.updatedDate = LocalDateTime.now()
        }
    }
}
