package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.user.UserRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.user.UserResponse
import com.example.serverarchive.service.user.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "회원 관련 API")
class UserController(private val userService: UserServiceImpl) {

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "회원가입", description = "신규 회원을 등록합니다.")
  fun registerUser(@RequestBody req: UserRequest): SingleResponse<UserResponse?> {
    var result = ResponseCode.ERROR
    var message = "Request Failed"

    val response = userService.createUser(req)
    response?.let {
      result = ResponseCode.SUCCESS
      message = "Successfully registered user"
    }

    return SingleResponse(
      result = result,
      message = message,
      data = response
    )
  }
}