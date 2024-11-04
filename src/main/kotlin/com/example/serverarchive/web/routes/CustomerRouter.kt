package com.example.serverarchive.web.routes

import com.example.serverarchive.domain.customer.entity.CustomerSearchOption
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
    companion object {
        const val BASE_PATH = "/customer"
        const val LIST_PATH = "/list"
        const val CREATE_PATH = "/create"
        const val UPDATE_PATH = "/update/{idx}"
    }

    @Bean
    fun customerRoutes() = router {
        (BASE_PATH).nest {
            GET(LIST_PATH, ::viewListPage)
            GET(CREATE_PATH, ::viewCreatePage)
            GET(UPDATE_PATH, ::viewUpdatePage)
        }
    }

    fun viewListPage(req: ServerRequest): ServerResponse  {
        val pageable = PaginationUtil.parseParams(req)
        val searchParams = mapOf(
            "selectedOption" to req.param("selectedOption").orElse(null), //TODO:selectedOption 확장시 `,`로 구분해서 추가시 뒤에서 `,`기준 spilt
            "searchKey" to req.param("searchKey").orElse(null),
            "startDate" to req.param("startDate").orElse(null),
            "endDate" to req.param("endDate").orElse(null)
        )

        val customers = customerService.getCustomerList(pageable, searchParams)

        val startIndex = (customers.number * customers.size) + 1
        val baseUrl = PaginationUtil.buildBaseUrl("$BASE_PATH$LIST_PATH", searchParams) //TODO:selectedOption 확장시 buildBaseUrl 수정필요
        val searchOptions = CustomerSearchOption.values().map {
            mapOf("value" to it.fieldName, "label" to it.label)
        }

        val data = mapOf(
            "message" to "업체 목록",
            "customers" to customers.content,
            "currentPage" to (customers.number + 1),
            "totalElements" to customers.totalElements,
            "size" to customers.size,
            "startIndex" to startIndex,
            "baseUrl" to baseUrl,
            "options" to searchOptions,
            "searchParams" to searchParams
        )

        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/customer/list", data)
    }

    fun viewCreatePage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "Create customer",
            "mode" to "create"
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
}