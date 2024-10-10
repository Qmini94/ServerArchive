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
            DELETE("/delete/{id}", ::deleteServer)
            GET("/update/{id}", ::updateServer)
        }
    }

    fun viewCreateServerPage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "create Server info"
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

    fun deleteServer(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id").toLong()
        val isDeleted = serverService.deleteServer(id)

        return if (isDeleted) {
            ServerResponse.ok().body("Server $id Info Deleted Complete.")
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).body("Server with ID $id not Found.")
        }
    }

    fun updateServer(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id").toLong()
        val server = serverService.findById(id)

        return if (server != null) {
            val data = mapOf(
                "message" to "update Server info",
                "server" to server
            )
            ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/create", data)
        } else {
            ServerResponse.status(HttpStatus.NOT_FOUND).body("Server with ID $id not found.")
        }
    }




}