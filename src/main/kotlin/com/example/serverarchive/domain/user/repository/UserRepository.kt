package com.example.serverarchive.domain.user.repository

import com.example.serverarchive.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	fun existsByUserId(userId: String): Boolean
	fun findByUserId(userId: String): User?
	fun findByIdx(idx: Int): User
}