package com.example.serverarchive.api.request.server

import com.example.serverarchive.domain.server.entity.Server
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "서버 정보")
class ServerRequest(
    @Schema(description = "IP 주소", nullable = false, required = true)
    val ip: String,
    @Schema(description = "접속 포트 번호", nullable = false, required = true)
    var port: String,
    @Schema(description = "root 계정 비밀번호", nullable = false, required = true)
    var rootPassword: String,
    @Schema(description = "사용자 계정", nullable = true, required = false)
    var serverUser: String? = null,
    @Schema(description = "사용자 계정 비밀번호", nullable = true, required = false)
    var password: String? = null,
    @Schema(description = "데이터베이스 이름", nullable = true, required = false)
    var databaseName: String?,
    @Schema(description = "메모", nullable = true, required = false)
    var memo: String? = null
) {
    fun toEntity(): Server {
        return Server(
            ip = this.ip,
            port = this.port,
            rootPassword = this.rootPassword,
            serverUser = this.serverUser,
            password = this.password,
            databaseName = this.databaseName,
            memo = this.memo,
            regId = "hjiwon98"
        )
    }
}