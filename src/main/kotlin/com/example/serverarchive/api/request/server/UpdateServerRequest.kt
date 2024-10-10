package com.example.serverarchive.api.request.server

data class UpdateServerRequest(
    val ip: String,
    val port: String,
    val serverUser: String,
    val rootPassword: String,
    val databaseName: String
)
