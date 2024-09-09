package com.example.serverarchive.domain.customer.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "customer")
class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Int? = null,

    @Column(name = "name", nullable = false, length = 30)
    val name: String,

    @Column(name = "server_idx", nullable = false, length = 30)
    val serverIdx: String,

    @Column(name = "service_idx", nullable = false, length = 30)
    val serviceIdx: String,

    @Column(name = "managers", nullable = false, length = 225)
    val managers: String,

    @Column(name = "code_type", nullable = false, length = 10)
    val codeType: Int,

    @Column(name = "memo", columnDefinition = "TEXT")
    val memo: String? = null,

    @Column(name = "created_date", nullable = false)
    val createdDate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "updated_date")
    val updatedDate: LocalDateTime? = null,

    @Column(name = "reg_id", nullable = false, length = 30)
    val regId: String,

    @Column(columnDefinition = "TEXT")
    val log: String? = null
)