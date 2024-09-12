package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.service.ServiceResponse
import com.example.serverarchive.service.service.ServiceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/service")
@Tag(name = "Service", description = "서비스 관련 API")
class ServiceController (private  val serviceService: ServiceService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "서비스 생성", description = "서비스를 등록합니다.")
    fun createCustomer(@RequestBody req: ServiceRequest): SingleResponse<ServiceResponse?> {
        return serviceService.createService(req)?.let { result ->
            SingleResponse(
                result = ResponseCode.SUCCESS,
                message = "Service created successfully",
                data = result
            )
        } ?: SingleResponse(
            result = ResponseCode.ERROR,
            message = "Failed to create service"
        )

//        val result = serviceService.createService(req)
//
//        return if (result != null) {
//            SingleResponse(
//                result = ResponseCode.SUCCESS,
//                message = "Service created successfully",
//                data = result,
//            )
//        } else {
//            SingleResponse(
//                result = ResponseCode.ERROR,
//                message = "Failed to create service"
//            )
//        }
    }
}