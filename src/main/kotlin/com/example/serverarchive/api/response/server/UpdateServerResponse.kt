package com.example.serverarchive.api.response.server

data class UpdateServerResponse(
    val ip: String,
    val port: String,
    val serverUser: String,
    val rootPassword: String,
    val databaseName: String
)
