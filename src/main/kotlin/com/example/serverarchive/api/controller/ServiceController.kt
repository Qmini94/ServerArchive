package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.request.service.ServiceDeleteRequest
import com.example.serverarchive.api.request.service.ServiceRequest
import com.example.serverarchive.api.request.service.ServiceUpdateRequest
import com.example.serverarchive.api.request.service.ServiceViewRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.api.response.service.ServiceDeleteResponse
import com.example.serverarchive.api.response.service.ServiceVIewResponse
import com.example.serverarchive.api.response.service.ServiceResponse
import com.example.serverarchive.domain.service.repository.ServiceRepository
import com.example.serverarchive.service.service.ServiceService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/service")
@Tag(name = "Service", description = "서비스 관련 API")
class ServiceController(private val serviceService: ServiceService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "서비스 생성", description = "서비스를 등록합니다.")
    fun createService(@RequestBody req: ServiceRequest): SingleResponse<ServiceResponse?> {
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
    }

    @DeleteMapping("/delete/{idx}")
    @Operation(summary = "업체삭제", description = "선택된 업체를 삭제합니다.")
    fun deleteService(@PathVariable idx: Int): SingleResponse<ServiceResponse?> {
        println("Delete service: $idx")
        val deletedCustomer = serviceService.deleteServiceById(idx)

        return if (deletedCustomer != null) {
            SingleResponse(
                    result = ResponseCode.SUCCESS,
                    message = "Service deleted successfully",
                    data = deletedCustomer
            )
        } else {
            SingleResponse(
                    result = ResponseCode.ERROR,
                    message = "Failed to delete service"
            )
        }
    }

    @PutMapping("/update/{idx}")
    @Operation(summary = "서비스 수정", description = "선택된 서비스의 정보를 수정합니다.")
    fun updateService(
            @PathVariable idx: Int,
            @RequestBody req: ServiceUpdateRequest
    ): SingleResponse<ServiceResponse?> {
        val updateService = serviceService.updateService(idx, req)

        return if (updateService != null) {
            SingleResponse(
                    result = ResponseCode.SUCCESS,
                    message = "Service updated successfully",
                    data = updateService
            )
        } else {
            SingleResponse(
                    result = ResponseCode.ERROR,
                    message = "Failed to update Service"
            )
        }
    }

//    @GetMapping("/view/{idx}")
//    @Operation(summary = "서비스 상세", description = "서비스 상세내역을 봅니다.")
//    fun ReadService(@RequestBody req: ServiceViewRequest): SingleResponse<ServiceVIewResponse?> {
//        return serviceService.viewService(req)?.let { result ->
//            SingleResponse(
//                result = ResponseCode.SUCCESS,
//                message = "Service viewed successfully",
//                data = result
//            )
//        } ?: SingleResponse(
//            result = ResponseCode.ERROR,
//            message = "Failed to view service"
//        )
//    }





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
