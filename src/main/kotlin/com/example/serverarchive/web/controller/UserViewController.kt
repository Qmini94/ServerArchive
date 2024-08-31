package com.example.serverarchive.web.controller

import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.service.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserViewController(private val userService: UserService) {

  @GetMapping("/register")
  fun showRegisterPage(): String {
    return "manager/user/register"
  }

  @PostMapping("/register")
  fun handleRegisterForm(@ModelAttribute user: User): String {
    userService.createUser(user)
    println("User registered: $user")
    return "redirect:/user/success"
  }

  @GetMapping("/login")
  fun showLoginPage(): String {
    return "client/user/login"
  }
}