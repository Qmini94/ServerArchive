package com.example.serverarchive.api.request.user

import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.util.ErrorCodes
import com.example.serverarchive.util.PasswordUtil
import java.time.LocalDateTime

class UserRequest(
  val userName: String,
  val userId: String,
  var password: String,
  val department: String? = null,
  val position: String? = null,
  val email: String? = null,
  val phone: String? = null,
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
    )
  }
}