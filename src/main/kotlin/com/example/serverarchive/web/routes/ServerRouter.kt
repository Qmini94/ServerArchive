package com.example.serverarchive.web.routes

import com.example.serverarchive.service.server.ServerServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router


@Configuration
class ServerRouter(private val serverService: ServerServiceImpl) {

    @Bean
    fun serverRoutes() = router {
        ("/server").nest {
            GET("/create", ::viewCreateServerPage)
            GET("/list", ::viewListServerPage)
            GET("/update/{idx}", ::updateServer)
        }
    }

    fun viewCreateServerPage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "create Server info",
            "mode" to "create"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/create", data)
    }

    fun viewListServerPage(req: ServerRequest): ServerResponse {
        val servers = serverService.getAllServers()
        val data = mapOf(
            "message" to "list Server info",
            "servers" to servers
        )

        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/list", data)
    }

    fun updateServer(req: ServerRequest): ServerResponse {
        val idx = req.pathVariable("idx").toLong()
        val server = serverService.findById(idx)

        return if (server != null) {
            val data = mapOf(
                "message" to "update Server info",
                "mode" to "update",
                "server" to server
            )
            ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/create", data)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).body("Server with ID $idx not found.")
        }
    }

}