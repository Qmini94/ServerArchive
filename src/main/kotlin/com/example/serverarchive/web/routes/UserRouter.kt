package com.example.serverarchive.web.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@Configuration
class UserRouter {

    @Bean
    fun userRoutes() = router {
        ("/user").nest {
            GET("/register", ::showRegisterPage)
            GET("/list", ::showSuccessPage)
            GET("/login", ::showLoginPage)
        }
    }

    fun showRegisterPage(req: ServerRequest): ServerResponse {
        val data = mapOf("message" to "Register New User")
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("manager/user/register", data)
    }

    fun showSuccessPage(req: ServerRequest): ServerResponse {
        val data = mapOf("message" to "Success New User")
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("manager/user/list", data)
    }

    fun showLoginPage(req: ServerRequest): ServerResponse {
        val data = mapOf("message" to "Login New User")
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/user/login", data)
    }
}