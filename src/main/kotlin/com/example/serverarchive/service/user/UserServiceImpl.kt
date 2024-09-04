package com.example.serverarchive.service.user

import com.example.serverarchive.api.request.user.UserRequest
import com.example.serverarchive.api.response.user.UserResponse
import com.example.serverarchive.api.response.user.UserResponse.Companion.toResponse
import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.domain.user.repository.UserRepository
import com.example.serverarchive.util.ErrorCodes
import com.example.serverarchive.util.PasswordUtil
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.data.jpa.domain.AbstractAuditable_.createdDate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

  override fun createUser(req: UserRequest): UserResponse? {

    // 중복 아이디 체크
    if (userRepository.existsByUserId(req.userId)) {
      throw IllegalArgumentException(ErrorCodes.getMessage(1004))
    }
    // 필수 값 체크 (아이디, 이름, 패스워드)
    if (req.validate()) {
      val currentRegId = "manager01" // TODO :: 하드코딩, 나중에 실 데이터로 수정
      val currentCreatedDate = LocalDateTime.now()

      val userToSave = req.toEntity()
      userToSave.regId = currentRegId

      return userRepository.save(userToSave).toResponse()
    } else {
      return null
    }
  }
}