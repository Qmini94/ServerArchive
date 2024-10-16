package com.example.serverarchive.web.routes

import com.example.serverarchive.api.response.customer.CustomerCreateResponse
import com.example.serverarchive.service.customer.CustomerService
import com.example.serverarchive.util.PaginationUtil
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
            GET("/create", ::viewCreatePage)
            GET("/update/{idx}", ::viewUpdatePage)
            GET("/list", ::viewListPage)
        }
    }

    fun viewCreatePage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "Create customer",
            "mode" to "create",
            "customer" to CustomerCreateResponse()
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/create", data)
    }

    fun viewUpdatePage(req: ServerRequest): ServerResponse {
        val idx = req.pathVariable("idx").toInt()
        val customer = customerService.getCustomerById(idx)
        val data = mapOf(
            "message" to "Update customer",
            "mode" to "update",
            "customer" to customer
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/create", data)
    }

    fun viewListPage(req: ServerRequest): ServerResponse  {
        val pageable = PaginationUtil.parseParams(req)
        val customers = customerService.getCustomerList(pageable)
        val startIndex = (customers.number * customers.size) + 1

        val data = mapOf(
            "pageTitle" to "업체 목록",
            "customers" to customers.content,
            "currentPage" to (customers.number + 1),
            "totalElements" to customers.totalElements,
            "size" to customers.size,
            "startIndex" to startIndex
        )

        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/list", data)
    }
}