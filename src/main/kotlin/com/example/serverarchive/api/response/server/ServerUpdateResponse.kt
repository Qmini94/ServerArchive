package com.example.serverarchive.api.response.server


data class ServerUpdateResponse(
    val ip: String,
    val port: String,
    val rootPassword: String,
    val serverUser: String?,
    val password: String?,
    val databaseName: String?,
    val memo: String?
)