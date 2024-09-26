package com.example.serverarchive.api.response.user

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원 아이디 중복 조회 결과")

data class UserCheckDuplicateUidResponse(
	val isAvailable: Boolean,
	val message: String
) {
	companion object {
		fun from(isAvailable: Boolean, message: String): UserCheckDuplicateUidResponse {
			return UserCheckDuplicateUidResponse(
				isAvailable = isAvailable,
				message = message
			)
		}
	}
}