package com.example.serverarchive.api.request.user

import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.util.ErrorCodes
import com.example.serverarchive.util.PasswordUtil
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원가입 정보")
data class UserRegisterRequest(
	@Schema(description = "회원명", nullable = false, required = true)
	val userName: String,
	@Schema(description = "회원ID", nullable = false, required = true)
	val userId: String,
	@Schema(description = "회원비밀번호", nullable = false, required = true)
	var password: String,
	@Schema(description = "부서", nullable = true, required = false)
	val department: String? = null,
	@Schema(description = "직책", nullable = true, required = false)
	val position: String? = null,
	@Schema(description = "이메일 주소", nullable = true, required = false)
	val email: String? = null,
	@Schema(description = "연락처", nullable = true, required = false)
	val phone: String? = null,
	@Schema(description = "레벨", nullable = true, required = false)
	val level: Int? = null,

	) {
	fun validate(): Boolean {
		requireNotNull(this.userId) { ErrorCodes.getMessage(1001) }
		requireNotNull(this.userName) { ErrorCodes.getMessage(1002) }
		requireNotNull(this.password) { ErrorCodes.getMessage(1003) }
		return true
	}

	fun toEntity(): User {
		val hashedPassword = PasswordUtil.hashPassword(this.password)

		return User(
			userName = this.userName,
			userId = this.userId,
			password = hashedPassword,
			department = this.department,
			position = this.position,
			email = this.email,
			phone = this.phone,
			level = this.level
		)
	}
}

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