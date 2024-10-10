package com.example.serverarchive.api.response.customer

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "업체 생성 정보")
class CustomerCreateResponse(
    @Schema(description = "업체 고유번호", nullable = true, required = false)
    val idx: Int? = null,

    @Schema(description = "업체명", nullable = true, required = false)
    val name: String? = null,

    @Schema(description = "서버ID", nullable = true, required = false)
    val serverIdx: String? = null,

    @Schema(description = "서비스ID", nullable = true, required = false)
    val serviceIdx: String? = null,

    @Schema(description = "담당자", nullable = true, required = false)
    val managers: String? = null,

    @Schema(description = "업체유형", nullable = true, required = false)
    val codeType: Int? = null,

    @Schema(description = "추가사항", nullable = true, required = false)
    val memo: String? = null,

    @Schema(description = "게시물 등록자 아이디", nullable = true, required = false)
    val regId: String? = null,

    @Schema(description = "업체등록일", nullable = true, required = false)
    val createdDate: LocalDateTime? = null,

    @Schema(description = "업체정보수정일", nullable = true, required = false)
    val updatedDate: LocalDateTime? = null
)
