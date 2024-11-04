package com.example.serverarchive.domain.customer.entity

import com.example.serverarchive.domain.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "customer")
class Customer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Int? = null,

    @Column(name = "name", nullable = false, length = 30)
    var name: String,

    @Column(name = "server_idx", nullable = false, length = 30)
    var serverIdx: String,

    @Column(name = "service_idx", nullable = false, length = 30)
    var serviceIdx: String,

    @Column(name = "managers", nullable = false, length = 225)
    var managers: String,

    @Column(name = "code_type", nullable = false, length = 10)
    var codeType: Int,

    @Column(name = "memo", columnDefinition = "TEXT")
    var memo: String? = null,

    @Column(name = "reg_id", nullable = false, length = 30)
    var regId: String,

    @Column(columnDefinition = "TEXT")
    var log: String? = null
) : BaseEntity()