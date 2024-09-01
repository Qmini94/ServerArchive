package com.example.serverarchive.api.response.user

import com.example.serverarchive.domain.user.entity.User
import java.time.LocalDateTime

data class UserResponse(
  val idx: Int? = null,
  val userName: String,
  val userId: String,
  val department: String? = null,
  val position: String? = null,
  val email: String? = null,
  val phone: String? = null,
  val level: Int? = null,
  val otp: String? = null,
  val createdDate: LocalDateTime? = null,
  val updatedDate: LocalDateTime? = null,
  val regId: String? = null,
  val log: String? = null
) {
  companion object {
    fun registerFrom(user: User): UserResponse {
      return UserResponse(
        idx = user.idx,
        userName = user.userName,
        userId = user.userId,
        department = user.department,
        position = user.position,
        email = user.email,
        phone = user.phone,
        level = user.level,
        otp = user.otp,
        createdDate = user.createdDate,
        updatedDate = user.updatedDate,
        regId = user.regId,
        log = user.log
      )
    }
  }
}