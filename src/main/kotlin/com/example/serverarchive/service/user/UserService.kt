package com.example.serverarchive.service.user

import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
  private val userRepository: UserRepository
) {

  fun createUser(user: User): User {
    return userRepository.save(user)
  }

}