package com.example.serverarchive.domain.user.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val idx: Int = 0,

	@Column(nullable = false, length = 10)
	val userName: String,

	@Column(nullable = false, length = 100)
	val userId: String,

	@Column(nullable = false, length = 255)
	var password: String,

	@Column(length = 30)
	val department: String? = null,

	@Column(length = 10)
	val position: String? = null,

	@Column(length = 50)
	val email: String? = null,

	@Column(length = 20)
	val phone: String? = null,

	@Column(length = 5)
	val level: String? = null,

	@Column(length = 50)
	val otp: String? = null,

	@Column
	val createdDate: LocalDateTime? = LocalDateTime.now(),

	@Column
	val updatedDate: LocalDateTime? = null,

	@Column(length = 30)
	var regId: String? = null,

	@Column(columnDefinition = "TEXT")
	val log: String? = null
)