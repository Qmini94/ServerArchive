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

  @Column(nullable = false, length = 10)
  val userId: String,

  @Column(nullable = false, length = 255)
  val password: String,

  @Column(length = 30)
  val department: String? = null,

  @Column(length = 10)
  val position: String? = null,

  @Column(length = 20)
  val email: String? = null,

  @Column(length = 10)
  val phone: String? = null,

  @Column
  val level: Int? = null,

  @Column(length = 50)
  val otp: String? = null,

  @Column
  val createdDate: LocalDateTime? = null,

  @Column
  val updatedDate: LocalDateTime? = null,

  @Column(length = 30)
  val regId: String? = null,

  @Column(columnDefinition = "TEXT")
  val log: String? = null
) {}