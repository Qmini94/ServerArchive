package com.example.serverarchive.api.request.user

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 아이디 조회")
data class UserCheckDuplicateUidRequest(
	@Schema(description = "회원ID", nullable = false, required = true)
	val userId: String
)