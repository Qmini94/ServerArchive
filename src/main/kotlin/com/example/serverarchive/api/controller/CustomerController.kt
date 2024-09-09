package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.customer.CustomerRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.service.customer.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.Objects

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "업체 관련 API")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "업체생성", description = "새로운 업체를 등록합니다.")
    fun createCustomer(@RequestBody req: CustomerRequest): SingleResponse<Objects> {
        val result = customerService.createCustomer(req)

        return if (result) {
            SingleResponse(
                result = ResponseCode.SUCCESS,
                message = "Customer created successfully"
            )
        } else {
            SingleResponse(
                result = ResponseCode.ERROR,
                message = "Failed to create customer"
            )
        }
    }
}