package com.example.serverarchive.web.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@Configuration
class ServerRouter {

    @Bean
    fun serverRoutes() = router {
        ("/server").nest {
            GET("/create", ::showCreateServerPage)
            GET("/list", ::showListServerPage)
        }
    }

    fun showCreateServerPage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "create Server info"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/create", data)
    }

    fun showListServerPage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "list Server info"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/list", data)
    }
}