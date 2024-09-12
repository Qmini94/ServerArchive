package com.example.serverarchive.domain.server.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "server")
class Server(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Int? = null,

    @Column(name = "ip", nullable = false, length = 30)
    val ip: String,

    @Column(name = "port", nullable = false, length = 30)
    var port: String,

    @Column(name = "server_user", nullable = false, length = 100)
    val serverUser: String,

    @Column(name = "root_password", nullable = false, length = 255)
    val rootPassword: String,

    @Column(name = "database_name", nullable = false, length = 30)
    val databaseName: String,

    @Column(name = "created_date", updatable = false)
    val createdDate: LocalDateTime? = null,

    @Column(name = "updated_date")
    var updatedDate: LocalDateTime? = null,

    @Column(name = "reg_id", nullable = false, length = 30)
    val regId: String? = null,

    @Column(columnDefinition = "TEXT")
    val log: String? = null
)