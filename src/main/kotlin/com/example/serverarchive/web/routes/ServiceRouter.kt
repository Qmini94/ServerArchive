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
            GET("/create", ::viewCreatePage)
            GET("/list", ::showListPage)
            GET("/update/{idx}", ::viewUpdatePage)
        }
    }

    fun viewCreatePage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "Create service"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/service/create", data)
    }

    fun showListPage(req: ServerRequest): ServerResponse {
        val services = serviceService.getAllServices()
        val data = mapOf(
            "message" to "Service List",
            "services" to services
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/service/list", data)
    }

    fun viewUpdatePage(req: ServerRequest): ServerResponse {
        val idx = req.pathVariable("idx").toInt()
        val service = serviceService.getServiceById(idx)
        val data = mapOf(
                "message" to "Update service",
                "mode" to "update",
                "service" to service
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/service/create", data)
    }

}