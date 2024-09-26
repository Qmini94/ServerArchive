package com.example.serverarchive.service.user


import JwtUtil
import com.example.serverarchive.api.request.user.UserCheckDuplicateUidRequest
import com.example.serverarchive.api.request.user.UserListRequest
import com.example.serverarchive.api.request.user.UserLoginRequest
import com.example.serverarchive.api.request.user.UserRegisterRequest
import com.example.serverarchive.api.response.user.UserCheckDuplicateUidResponse
import com.example.serverarchive.api.response.user.UserListResponse
import com.example.serverarchive.api.response.user.UserListResponse.Companion.toListResponse
import com.example.serverarchive.api.response.user.UserLoginResponse
import com.example.serverarchive.api.response.user.UserLoginResponse.Companion.toUserLoginResponse
import com.example.serverarchive.api.response.user.UserRegisterResponse
import com.example.serverarchive.api.response.user.UserRegisterResponse.Companion.toResponse
import com.example.serverarchive.domain.user.entity.User
import com.example.serverarchive.domain.user.repository.UserRepository
import com.example.serverarchive.util.ErrorCode
import com.example.serverarchive.util.ErrorCodes
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
	private val userRepository: UserRepository,
	private val jwtUtil: JwtUtil
) : UserService {

	override fun createUser(req: UserRegisterRequest): UserRegisterResponse? {

		// 중복 아이디 체크
		if (userRepository.existsByUserId(req.userId)) {
			throw IllegalArgumentException(ErrorCode.ALREADY_EXISTS.name)
//			throw IllegalArgumentException(ErrorCodes.getMessage(1004))
		}

		// 필수 값 체크 (아이디, 이름, 패스워드)
		if (req.validate()) {
			val currentRegId = "manager01" // TODO :: 하드코딩, 나중에 실 데이터로 수정

			val userToSave = req.toEntity()
			userToSave.regId = currentRegId

			return userRepository.save(userToSave).toResponse()
		} else {
			return null
		}
	}

	override fun loginUser(req: UserLoginRequest): UserLoginResponse? {
		// 아이디 유무 체크
		val user =
			userRepository.findByUserId(req.userId) ?: throw IllegalArgumentException(ErrorCodes.getMessage(1005))
		if (req.validate()) {
			val passwordEncoder = BCryptPasswordEncoder()
			if (!passwordEncoder.matches(req.password, user.password)) {
				throw IllegalArgumentException(ErrorCodes.getMessage(1006))
			}
			// JWT 토큰 발급
			val token = jwtUtil.generateToken(user.userId)
			return user.toUserLoginResponse(token)

		} else {
			return null
		}
	}

	override fun checkDuplicateUid(req: UserCheckDuplicateUidRequest): UserCheckDuplicateUidResponse? {
		return if (userRepository.existsByUserId(req.userId)) {
			UserCheckDuplicateUidResponse.from(false, ErrorCodes.getMessage(1004))
		} else {
			UserCheckDuplicateUidResponse.from(true, "사용가능한 아이디입니다.")
		}
	}

	override fun getUserList(req: UserListRequest): List<UserListResponse>? {
		val pageable: Pageable = PageRequest.of(req.page - 1, req.size)
		val users: Page<User> = userRepository.findAll(pageable)

		return users.content.map { user ->
			user.toListResponse(
				currentPage = users.number + 1,
				totalPages = users.totalPages,
				nextPage = if (users.number + 1 < users.totalPages) users.number + 2 else null
			)
		}
	}
}