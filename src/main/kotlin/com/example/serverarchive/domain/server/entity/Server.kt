package com.example.serverarchive.domain.server.entity

import com.example.serverarchive.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "server")
class Server(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Int? = null,

    @Column(name = "ip", nullable = false, length = 30)
    var ip: String,

    @Column(name = "port", nullable = false, length = 30)
    var port: String,

    @Column(name = "root_password", nullable = false, length = 255)
    var rootPassword: String,

    @Column(name = "server_user", nullable = true, length = 100)
    var serverUser: String? = null,

    @Column(name = "password", nullable = true, length = 255)
    var password: String? = null,

    @Column(name = "database_name", nullable = true, length = 30)
    var databaseName: String? = null,

    @Column(name = "memo", nullable = true, columnDefinition = "TEXT")
    var memo: String? = null,

    @Column(name = "reg_id", nullable = true, length = 30)
    val regId: String? = null,

    @Column(columnDefinition = "TEXT", nullable = true)
    val log: String? = null
) : BaseEntity()
