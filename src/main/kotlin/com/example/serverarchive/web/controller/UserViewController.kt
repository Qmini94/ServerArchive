package com.example.serverarchive.web.controller

import com.example.serverarchive.service.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserViewController(private val userService: UserService) {

  @GetMapping("/register")
  fun showRegisterPage(): String {
    return "manager/user/register"
  }

  @GetMapping("/list")
  fun showSuccessPage(): String {
    return "manager/user/list"
  }

  @GetMapping("/login")
  fun showLoginPage(): String {
    return "client/user/login"
  }
}