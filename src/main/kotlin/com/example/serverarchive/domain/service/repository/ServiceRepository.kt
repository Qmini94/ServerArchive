package com.example.serverarchive.domain.service.repository

import com.example.serverarchive.domain.service.entity.Service
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository: JpaRepository<Service, Int> {
    fun existsServiceByDomainUrl(domainUrl: String): Boolean
}