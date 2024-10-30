package com.example.serverarchive.api.request.server

data class ServerUpdateRequest(
    val ip: String,
    val port: String,
    val serverUser: String,
    val rootPassword: String,
    val databaseName: String,
    val memo: String
)
