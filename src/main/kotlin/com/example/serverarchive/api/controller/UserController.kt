package com.example.serverarchive.api.controller

import com.example.serverarchive.api.request.user.UserRequest
import com.example.serverarchive.api.response.user.UserResponse
import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.service.user.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserServiceImpl) {

  @PostMapping("/register")
  fun registerUser(@RequestBody req: UserRequest): ResponseEntity<UserResponse?> {
    val createdUser = userService.createUser(req)
    return ResponseEntity.ok(createdUser)
  }
}