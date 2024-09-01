package com.example.serverarchive.service.user

import com.example.serverarchive.domain.user.entity.User

interface UserService {
  fun createUser(user: User): User
}