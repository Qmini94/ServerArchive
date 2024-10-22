package com.example.serverarchive.api.response.customer

import com.example.serverarchive.domain.customer.entity.Customer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.format.DateTimeFormatter

@Schema(description = "업체 정보")
class CustomerResponse(
    @Schema(description = "업체 고유번호", nullable = false, required = true)
    val idx: Int? = null,

    @Schema(description = "업체명", nullable = false, required = true)
    val name: String,

    @Schema(description = "서버ID", nullable = false, required = true)
    val serverIdx: String,

    @Schema(description = "서비스ID", nullable = false, required = true)
    val serviceIdx: String,

    @Schema(description = "담당자", nullable = false, required = true)
    val managers: String,

    @Schema(description = "업체유형", nullable = false, required = true)
    val codeType: Int,

    @Schema(description = "추가사항", nullable = false, required = true)
    val memo: String? = null,

    @Schema(description = "게시물 등록자 아이디", nullable = false, required = true)
    val regId: String,

    @Schema(description = "업체등록일", nullable = true, required = false)
    val createdDate: String? = null,

    @Schema(description = "업체정보수정일", nullable = true, required = false)
    val updatedDate: String? = null

    ){
    companion object{
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        fun Customer.toResponse(): CustomerResponse {
            return CustomerResponse(
                idx =  this.idx,
                name = this.name,
                serverIdx = this.serverIdx,
                serviceIdx = this.serviceIdx,
                managers = this.managers,
                codeType = this.codeType,
                memo = this.memo,
                regId = this.regId,
                createdDate = this.createdDate?.format(formatter),
                updatedDate = this.updatedDate?.format(formatter)
            )
        }
    }
}