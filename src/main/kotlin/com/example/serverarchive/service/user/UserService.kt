package com.example.serverarchive.service.user


import com.example.serverarchive.api.request.user.UserCheckDuplicateUidRequest
import com.example.serverarchive.api.request.user.UserListRequest
import com.example.serverarchive.api.request.user.UserLoginRequest
import com.example.serverarchive.api.request.user.UserRegisterRequest
import com.example.serverarchive.api.response.user.UserCheckDuplicateUidResponse
import com.example.serverarchive.api.response.user.UserListResponse
import com.example.serverarchive.api.response.user.UserLoginResponse
import com.example.serverarchive.api.response.user.UserRegisterResponse


interface UserService {
	fun createUser(req: UserRegisterRequest): UserRegisterResponse?
	fun loginUser(req: UserLoginRequest): UserLoginResponse?
	fun checkDuplicateUid(req: UserCheckDuplicateUidRequest): UserCheckDuplicateUidResponse?
	fun getUserList(req: UserListRequest): List<UserListResponse>?
	fun getUserByIdx(idx: Int): UserRegisterResponse
}