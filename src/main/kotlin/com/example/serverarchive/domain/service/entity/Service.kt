package com.example.serverarchive.domain.service.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "service")
class Service(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Int = 0,

    @Column(name = "domain_url", nullable = false, length = 50) // url
    val domainUrl: String,

    @Column(name = "domain_resister", nullable = false, length = 50) // 도메인 등록일?
    val domainResister: String,

    @Column(name = "certificate_issuer", length = 50) // 인증서 구매처
    val certificateIssuer: String,

    @Column(name="certificate_renewal_date",length = 20) // 인증서 갱신일
    var certificateRenewalDate: String,

    @Column(name="webmail_cnt") // 웹메일 계정 총갯수
    var webmailCnt: Int? = null,

    @Column(name="webmail_account") // 웹메일 계정
    var webmailAccount: String? = null,

    @Column(name="created_date") // 생성일
    val createdDate: LocalDateTime? = LocalDateTime.now(),

    @Column(name="updated_date") // 업데이트
    val updatedDate: LocalDateTime? = null,

    @Column(columnDefinition = "TEXT") // 서비스 히스토리
    var memo: String? = null,

    @Column(columnDefinition = "TEXT")
    var log: String? = null,

){}