package com.example.serverarchive.service.user

import com.example.serverarchive.api.response.user.UserResponse
import com.example.serverarchive.api.response.user.UserResponse.Companion.toResponse
import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.domain.user.repository.UserRepository
import com.example.serverarchive.util.ErrorCodes
import com.example.serverarchive.util.PasswordUtil
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

  override fun createUser(user: User): UserResponse {

    // 중복 아이디 체크
    if (userRepository.existsByUserId(user.userId)) {
      throw IllegalArgumentException(ErrorCodes.getMessage(1004))
    }
    // 필수 값 체크 (아이디, 이름, 패스워드)
    requireNotNull(user.userId) { ErrorCodes.getMessage(1001) }
    requireNotNull(user.userName) { ErrorCodes.getMessage(1002) }
    requireNotNull(user.password) { ErrorCodes.getMessage(1003) }

    val hashedPassword = PasswordUtil.hashPassword(user.password)
    val currentRegId = "manager01" // TODO :: 하드코딩, 나중에 실 데이터로 수정
    val currentCreatedDate = LocalDateTime.now()

    val userToSave = user.copy(
      password = hashedPassword,
      createdDate = currentCreatedDate,
      regId = currentRegId,
      log = "$currentRegId-$currentCreatedDate 등록"
    )
    return userRepository.save(userToSave).toResponse()
  }
}