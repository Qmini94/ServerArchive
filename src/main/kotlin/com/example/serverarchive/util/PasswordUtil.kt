package com.example.serverarchive.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object PasswordUtil {
  private val encoder = BCryptPasswordEncoder()

  fun hashPassword(rawPassword: String): String {
    return encoder.encode(rawPassword)
  }

  fun matches(rawPassword: String, encodedPassword: String): Boolean {
    return encoder.matches(rawPassword, encodedPassword)
  }
}