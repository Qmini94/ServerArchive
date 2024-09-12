package com.example.serverarchive.web.routes

import com.example.serverarchive.service.service.ServiceService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

import org.springframework.web.servlet.function.router

@Configuration
class ServiceRouter (private val serviceService: ServiceService){

    @Bean
    fun serviceRoutes() = router {
        ("/service").nest {
            GET("/create", ::showCreatePage)
            GET("/list", ::showListPage)
        }
    }

    fun showCreatePage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "Create service"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/service/create", data)
    }

    fun showListPage(req: ServerRequest): ServerResponse {
//        val service = serviceService.getAllService()
        val data = mapOf(
            "message" to "Service List",
            "service" to null
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/list", data)
    }
}