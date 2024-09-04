package com.example.serverarchive.service.user

import com.example.serverarchive.api.request.user.UserRequest
import com.example.serverarchive.api.response.user.UserResponse
import com.example.serverarchive.domain.user.entity.User

interface UserService {
  fun createUser(req: UserRequest): UserResponse?
}