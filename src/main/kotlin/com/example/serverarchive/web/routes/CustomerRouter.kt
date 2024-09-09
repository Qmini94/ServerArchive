package com.example.serverarchive.web.routes

import com.example.serverarchive.service.customer.CustomerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@Configuration
class CustomerRouter(private val customerService: CustomerService) {

    @Bean
    fun customerRoutes() = router {
        ("/customer").nest {
            GET("/create", ::showCreatePage)
            GET("/list", ::showListPage)
        }
    }

    fun showCreatePage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "Create customer"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/create", data)
    }

    fun showListPage(req: ServerRequest): ServerResponse  {
        val customers = customerService.getAllCustomers()
        val data = mapOf(
            "message" to "Customer List",
            "customers" to customers
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/list", data)
    }
}