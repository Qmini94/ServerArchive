package com.example.serverarchive.service.user


import com.example.serverarchive.api.request.user.UserLoginRequest
import com.example.serverarchive.api.request.user.UserRegisterRequest
import com.example.serverarchive.api.response.user.UserLoginResponse
import com.example.serverarchive.api.response.user.UserRegisterResponse


interface UserService {
	fun createUser(req: UserRegisterRequest): UserRegisterResponse?
	fun loginUser(req: UserLoginRequest): UserLoginResponse?
}