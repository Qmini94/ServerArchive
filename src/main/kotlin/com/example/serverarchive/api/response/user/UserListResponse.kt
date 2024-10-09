package com.example.serverarchive.api.response.user

import com.example.serverarchive.domain.user.entity.User
import io.swagger.v3.oas.annotations.media.Schema

data class UserListResponse(
	@Schema(description = "회원고유번호", nullable = false, required = true)
	val idx: Int,

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
	val level: String? = null,

	@Schema(description = "마지막 로그인", nullable = true, required = false)
	val lastLogin: String? = null,
) {
	companion object {
		fun User.toListResponse(): UserListResponse {
			return UserListResponse(
				idx = this.idx,
				userName = this.userName,
				userId = this.userId,
				department = this.department,
				position = this.position,
				email = this.email,
				phone = this.phone,
				level = this.level
			)
		}
	}
}