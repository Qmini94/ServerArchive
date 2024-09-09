package com.example.serverarchive.service.user

import com.example.serverarchive.api.request.user.UserLoginRequest
import com.example.serverarchive.api.request.user.UserRegisterRequest
import com.example.serverarchive.api.response.user.UserResponse


interface UserService {
	fun createUser(req: UserRegisterRequest): UserResponse?
	fun loginUser(req: UserLoginRequest): UserResponse?
}