package com.example.serverarchive.api.response.user

import com.example.serverarchive.domain.user.entity.User
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "회원정보")
data class UserResponse(
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
  @Schema(description = "OTP 인증 Key 값", nullable = true, required = false)
  val otp: String? = null,
  @Schema(description = "회원가입일", nullable = true, required = false)
  val createdDate: LocalDateTime? = null,
  @Schema(description = "정보수정일", nullable = true, required = false)
  val updatedDate: LocalDateTime? = null,
  val regId: String? = null,
  val log: String? = null
) {
  companion object {
    fun User.toResponse(): UserResponse {
      return UserResponse(
//        idx = user.idx,
        userName = this.userName,
        userId = this.userId,
        department = this.department,
        position = this.position,
        email = this.email,
        phone = this.phone,
        level = this.level,
      )
    }
    fun registerFrom(user: User): UserResponse {
      return UserResponse(
//        idx = user.idx,
        userName = user.userName,
        userId = user.userId,
        department = user.department,
        position = user.position,
        email = user.email,
        phone = user.phone,
        level = user.level,
//        otp = user.otp,
//        createdDate = user.createdDate,
//        updatedDate = user.updatedDate,
//        regId = user.regId,
//        log = user.log
      )
    }
  }
}