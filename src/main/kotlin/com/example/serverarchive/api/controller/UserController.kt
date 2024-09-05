package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.user.UserRequest
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.user.UserResponse
import com.example.serverarchive.service.user.UserServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserServiceImpl) {

  @PostMapping("/register")
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