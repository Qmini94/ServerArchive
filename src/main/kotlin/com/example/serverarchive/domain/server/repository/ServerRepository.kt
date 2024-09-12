package com.example.serverarchive.domain.server.repository

import com.example.serverarchive.domain.server.entity.Server
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServerRepository : JpaRepository<Server, Long> {
}