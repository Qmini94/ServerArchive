package com.example.serverarchive.api.response.server

import com.example.serverarchive.domain.server.entity.Server
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "서버정보")
class ServerResponse(
    val idx: Int? = null,
    @Schema(description = "IP 주소", nullable = false, required = true)
    val ip: String,
    @Schema(description = "접속 포트 번호", nullable = false, required = true)
    val port: String,
    @Schema(description = "root 계정 비밀번호", nullable = true, required = false)
    val rootPassword: String,
    @Schema(description = "사용자 계정", nullable = true, required = false)
    val serverUser: String?,
    @Schema(description = "사용자 계정 비밀번호", nullable = true, required = false)
    val password: String?,
    @Schema(description = "데이터베이스 이름", nullable = true, required = false)
    val databaseName: String?,
    @Schema(description = "메모", nullable = true, required = false)
    var memo: String? = null,
    @Schema(description = "서버 신규 등록일자", nullable = true, required = false)
    val createdDate: LocalDateTime? = null,
    @Schema(description = "서버 정보 수정일자", nullable = true, required = false)
    val updatedDate: LocalDateTime? = null,
    val regId: String? = null,
    val log: String? = null
) {
    companion object {
        fun Server.toResponse(): ServerResponse {
            return ServerResponse(
                idx = this.idx,
                ip = this.ip,
                port = this.port,
                rootPassword = this.rootPassword,
                serverUser = this.serverUser,
                password = this.password,
                databaseName = this.databaseName,
                memo = this.memo,
                createdDate = this.createdDate,
                updatedDate = this.updatedDate,
                regId = this.regId,
                log = this.log
            )
        }
    }
}