package com.example.serverarchive.domain.service.repository


import com.example.serverarchive.domain.service.entity.Service
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository: JpaRepository<Service, Int>, JpaSpecificationExecutor<Service> {
    fun existsServiceByDomainUrl(domainUrl: String): Boolean
    fun existsServiceByIdx(idx: Int): Boolean
    fun deleteServiceByIdx(idx: Int): Boolean
}