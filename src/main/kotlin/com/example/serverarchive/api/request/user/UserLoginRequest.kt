package com.example.serverarchive.api.request.user

import com.example.serverarchive.util.ErrorCodes
import com.example.serverarchive.util.PasswordUtil
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "로그인 정보")
data class UserLoginRequest(
	@Schema(description = "회원ID", nullable = false, required = true)
	val userId: String,
	@Schema(description = "회원비밀번호", nullable = false, required = true)
	var password: String,
	@Schema(description = "opt", nullable = true, required = false)
	val otp: String? = null,
) {

	fun validate(): Boolean {
		requireNotNull(this.userId) { ErrorCodes.getMessage(1001) }
		requireNotNull(this.password) { ErrorCodes.getMessage(1003) }
		return true
	}
}