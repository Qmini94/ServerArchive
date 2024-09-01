package com.example.serverarchive.domain.user.repository

import com.example.serverarchive.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
  fun existsByUserId(userId: String): Boolean
  fun findByUserId(userId: String): User?
}