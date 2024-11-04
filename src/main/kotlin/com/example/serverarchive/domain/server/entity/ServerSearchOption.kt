package com.example.serverarchive.domain.server.entity

enum class ServerSearchOption(val fieldName: String, val label: String) {
    ip("ip", "IP 주소"),
    serverUser("serverUser", "사용자 계정"),
    databaseName("databaseName", "데이터베이스 이름"),
    memo("memo", "메모")
}