package com.example.serverarchive.api.request.server

import com.example.serverarchive.domain.server.entity.Server
import com.example.serverarchive.util.ErrorCodes
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "서버 정보")
class ServerRequest(
    @Schema(description = "IP 주소", nullable = false, required = true)
    val ip: String,
    @Schema(description = "접속 포트 번호", nullable = false, required = true)
    val port: String,
    @Schema(description = "사용자 계정", nullable = false, required = true)
    var serverUser: String,
    @Schema(description = "데이터베이스 이름", nullable = false, required = true)
    var databaseName: String,
    @Schema(description = "root 계정 비밀번호", nullable = false, required = true)
    val rootPassword: String,
) {
    fun validate(): Boolean {
        requireNotNull(this.ip) { ErrorCodes.getMessage(1006) }
        requireNotNull(this.port) { ErrorCodes.getMessage(1007) }
        requireNotNull(this.serverUser) { ErrorCodes.getMessage(1008) }
        requireNotNull(this.rootPassword) { ErrorCodes.getMessage(1003) }
        requireNotNull(this.databaseName) { ErrorCodes.getMessage(1009) }

        return true
    }

    fun toEntity(): Server {
        return Server(
            ip = this.ip,
            port = this.port,
            serverUser = this.serverUser,
            databaseName = this.databaseName,
            rootPassword = this.rootPassword,
        )
    }
}