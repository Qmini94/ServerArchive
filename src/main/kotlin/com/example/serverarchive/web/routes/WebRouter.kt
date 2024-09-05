package com.example.serverarchive.web.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router

@Configuration
class WebRouter {

    @Bean
    fun routes() = router {
        ("/").nest {
            GET("", ::home)
        }
    }

    fun home(req: ServerRequest): ServerResponse {
        val data = mapOf("message" to "MainPage")
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("index", data)
    }
}