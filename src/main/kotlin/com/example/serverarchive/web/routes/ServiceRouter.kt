package com.example.serverarchive.web.routes

import com.example.serverarchive.domain.customer.entity.CustomerSearchOption
import com.example.serverarchive.domain.service.entity.ServiceSearchOption
import com.example.serverarchive.service.service.ServiceService
import com.example.serverarchive.util.PaginationUtil
import com.example.serverarchive.web.routes.CustomerRouter.Companion.BASE_PATH
import com.example.serverarchive.web.routes.CustomerRouter.Companion.LIST_PATH
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
            GET("/list", ::viewListPage)
            GET("/update/{idx}", ::viewUpdatePage)
        }
    }

    fun viewCreatePage(req: ServerRequest): ServerResponse {
        val data = mapOf(
            "message" to "Create service",
            "mode" to "create"
        )
        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/service/create", data)
    }

    fun viewListPage(req: ServerRequest): ServerResponse {
        val pageable = PaginationUtil.parseParams(req)
        val searchParams = mapOf(
            "selectedOption" to req.param("selectedOption").orElse(null), //TODO:selectedOption 확장시 `,`로 구분해서 추가시 뒤에서 `,`기준 spilt
            "searchKey" to req.param("searchKey").orElse(null),
            "startDate" to req.param("startDate").orElse(null),
            "endDate" to req.param("endDate").orElse(null)
        )
        val services = serviceService.getServiceList(pageable,searchParams)

        val startIndex = (services.number * services.size) + 1
        val baseUrl = PaginationUtil.buildBaseUrl("/service/list", searchParams) //TODO:selectedOption 확장시 buildBaseUrl 수정필요
        val searchOptions = ServiceSearchOption.values().map {
            mapOf("value" to it.fieldName, "label" to it.label)
        }

        val data = mapOf(
            "message" to "Service List",
            "services" to services,
            "currentPage" to (services.number + 1),
            "totalElements" to services.totalElements,
            "size" to services.size,
            "startIndex" to startIndex,
            "baseUrl" to baseUrl,
            "options" to searchOptions,
            "searchParams" to searchParams
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