package com.example.serverarchive.domain.customer.repository

import com.example.serverarchive.domain.customer.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Int> {
}