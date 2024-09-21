package com.example.serverarchive.api.response.user

import com.example.serverarchive.domain.user.entity.User
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인한 회원 정보")

data class UserLoginResponse(
	val idx: Int,
	@Schema(description = "회원명", nullable = false, required = true)
	val userName: String,
	@Schema(description = "회원ID", nullable = false, required = true)
	val userId: String,
	@Schema(description = "회원레벨", nullable = true, required = false)
	val level: Int? = null,
	@Schema(description = "OTP 인증 Key 값", nullable = true, required = false)
	val otp: String? = null,
	@Schema(description = "JWT 토큰", nullable = false, required = true)
	val token: String // JWT 토큰 필드 추가
) {
	companion object {
		fun User.toUserLoginResponse(token: String): UserLoginResponse {
			return UserLoginResponse(
				idx = this.idx,
				userName = this.userName,
				userId = this.userId,
				level = this.level,
				otp = this.otp,
				token = token
			)
		}
	}
}