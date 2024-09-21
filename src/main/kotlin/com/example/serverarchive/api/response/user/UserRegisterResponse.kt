package com.example.serverarchive.api.response.user

import com.example.serverarchive.domain.user.entity.User
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "회원정보")
data class UserRegisterResponse(
	val idx: Int? = null,
	@Schema(description = "회원명", nullable = false, required = true)
	val userName: String,
	@Schema(description = "회원ID", nullable = false, required = true)
	val userId: String,
	@Schema(description = "부서", nullable = true, required = false)
	val department: String? = null,
	@Schema(description = "직책", nullable = true, required = false)
	val position: String? = null,
	@Schema(description = "이메일 주소", nullable = true, required = false)
	val email: String? = null,
	@Schema(description = "연락처", nullable = true, required = false)
	val phone: String? = null,
	@Schema(description = "회원레벨", nullable = true, required = false)
	val level: Int? = null,
) {
	companion object {
		fun User.toResponse(): UserRegisterResponse {
			return UserRegisterResponse(
				userName = this.userName,
				userId = this.userId,
				department = this.department,
				position = this.position,
				email = this.email,
				phone = this.phone,
				level = this.level,
			)
		}

		fun registerFrom(user: User): UserRegisterResponse {
			return UserRegisterResponse(
				userName = user.userName,
				userId = user.userId,
				department = user.department,
				position = user.position,
				email = user.email,
				phone = user.phone,
				level = user.level,
			)
		}
	}
}