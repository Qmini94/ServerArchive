package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.customer.CustomerCreateRequest
import com.example.serverarchive.api.request.customer.CustomerUpdateRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.customer.CustomerResponse
import com.example.serverarchive.service.customer.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "업체 관련 API")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "업체생성", description = "새로운 업체를 등록합니다.")
    fun createCustomer(@RequestBody req: CustomerCreateRequest): SingleResponse<CustomerResponse?> {
        return customerService.createCustomer(req)?.let { customerResponse ->
            SingleResponse(
                result = ResponseCode.SUCCESS,
                message = "Customer created successfully",
                data = customerResponse
            )
        } ?: SingleResponse(
            result = ResponseCode.ERROR,
            message = "Failed to create customer"
        )
    }

    @PutMapping("/update/{idx}")
    @Operation(summary = "업체수정", description = "선택된 업체의 정보를 수정합니다.")
    fun updateCustomer(
        @PathVariable idx: Int,
        @RequestBody req: CustomerUpdateRequest
    ): SingleResponse<CustomerResponse?> {
        return try {
            val updatedCustomer = customerService.updateCustomer(idx, req)
            SingleResponse(
                result = ResponseCode.SUCCESS,
                message = "Customer updated successfully",
                data = updatedCustomer
            )
        } catch (e: IllegalArgumentException) {
            SingleResponse(
                result = ResponseCode.ERROR,
                message = e.message ?: "Failed to update customer"
            )
        } catch (e: Exception) {
            SingleResponse(
                result = ResponseCode.ERROR,
                message = "An unknown error occurred"
            )
        }
    }

    @DeleteMapping("/delete/{idx}")
    @Operation(summary = "업체삭제", description = "선택된 업체를 삭제합니다.")
    fun deleteCustomer(@PathVariable idx: Int): SingleResponse<CustomerResponse?> {
        println("Deleting customer with idx: $idx")
        return try {
            val deletedCustomer = customerService.deleteCustomerById(idx)

            SingleResponse(
                result = ResponseCode.SUCCESS,
                message = "Customer deleted successfully",
                data = deletedCustomer
            )
        } catch (e: IllegalArgumentException) {
            SingleResponse(
                result = ResponseCode.ERROR,
                message = e.message ?: "Invalid request for deleting customer"
            )
        } catch (e: Exception) {
            SingleResponse(
                result = ResponseCode.ERROR,
                message = "An unknown error occurred during customer deletion"
            )
        }
    }
}