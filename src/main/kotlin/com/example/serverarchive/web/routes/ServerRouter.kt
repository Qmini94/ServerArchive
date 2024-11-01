package com.example.serverarchive.web.routes

import com.example.serverarchive.service.server.ServerService
import com.example.serverarchive.util.PaginationUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router


@Configuration
class ServerRouter(private val serverService: ServerService) {

    @Bean
    fun serverRoutes() = router {
        ("/server").nest {
            GET("/list", ::viewListServerPage)
            GET("/create", ::viewCreateServerPage)
            GET("/update/{idx}", ::viewUpdateServerPage)
        }
    }

    fun viewListServerPage(req: ServerRequest): ServerResponse {
        val pageable = PaginationUtil.parseParams(req)
        val servers = serverService.getAllServers(pageable)
        val startIndex = (servers.number * servers.size) + 1
        val baseUrl = PaginationUtil.buildBaseUrl("/server/list", emptyMap()) //TODO:selectedOption 확장시 buildBaseUrl 수정필요

        val data = mapOf(
            "message" to "list Server info",
            "servers" to servers.content,
            "currentPage" to (servers.number + 1),
            "totalElements" to servers.totalElements,
            "size" to servers.size,
            "startIndex" to startIndex,
            "baseUrl" to baseUrl
        )

        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/list", data)
    }

    fun viewCreateServerPage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "create Server info",
            "mode" to "create"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/server/create", data)
    }

    fun viewUpdateServerPage(req: ServerRequest): ServerResponse {
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