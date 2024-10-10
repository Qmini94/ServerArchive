package com.example.serverarchive.api.controller


import JwtUtil
import com.example.serverarchive.api.request.user.UserCheckDuplicateUidRequest
import com.example.serverarchive.api.request.user.UserListRequest
import com.example.serverarchive.api.request.user.UserLoginRequest
import com.example.serverarchive.api.request.user.UserRegisterRequest
import com.example.serverarchive.api.response.PageResponse
import com.example.serverarchive.api.response.ResponseCode
import com.example.serverarchive.api.response.SingleResponse
import com.example.serverarchive.api.response.user.UserCheckDuplicateUidResponse
import com.example.serverarchive.api.response.user.UserListResponse
import com.example.serverarchive.api.response.user.UserLoginResponse
import com.example.serverarchive.api.response.user.UserRegisterResponse
import com.example.serverarchive.service.user.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "회원 관련 API")
class UserController(
	private val userService: UserServiceImpl,
	private val jwtUtil: JwtUtil
) {
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "회원목록", description = "회원 목록을 조회합니다.")
	fun getUsers(
		@Parameter(name = "page", description = "시작 페이지", `in` = ParameterIn.QUERY)
		page: Int? = 1,
		@Parameter(name = "size", description = "페이지 출력 수", `in` = ParameterIn.QUERY)
		size: Int? = 10,
		@Parameter(name = "searchKey", description = "검색키", `in` = ParameterIn.QUERY)
		searchKey: String? = null,
		@Parameter(name = "keyword", description = "검색어", `in` = ParameterIn.QUERY)
		keyword: String? = null,
	): PageResponse<UserListResponse>? {
		var result = ResponseCode.ERROR
		var message = "Request Failed"

		val response = userService.getUserList(UserListRequest(page ?: 1, size ?: 10, searchKey ?: "", keyword))
		response?.let {
			result = ResponseCode.SUCCESS
			message = "Successfully search user"
		}

		return PageResponse(
			result = result,
			message = message,
			data = response
		)
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "회원가입", description = "신규 회원을 등록합니다.")
	fun registerUser(@RequestBody req: UserRegisterRequest): SingleResponse<UserRegisterResponse?> {
		var result = ResponseCode.ERROR
		var message = "Request Failed"

		val response = userService.createUser(req)
		response?.let {
			result = ResponseCode.SUCCESS
			message = "Successfully registered user"
		}

		return SingleResponse(
			result = result,
			message = message,
			data = response
		)
	}

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "로그인", description = "로그인을 합니다.")
	fun login(@RequestBody req: UserLoginRequest, res: HttpServletResponse): SingleResponse<UserLoginResponse?> {
		var result = ResponseCode.ERROR
		var message = "Request Failed"

		val response = userService.loginUser(req)
		response?.let {
			result = ResponseCode.SUCCESS
			message = "Login Success"

			val token = jwtUtil.generateToken(response.userId)
			setJwtTokenCookie(res, token)
		}

		return SingleResponse(
			result = result,
			message = message,
			data = response
		)
	}

	@PostMapping("/renew-token")
	@Operation(summary = "토큰갱신", description = "JWT 토큰을 갱신합니다.")
	fun renewToken(req: HttpServletRequest, res: HttpServletResponse): SingleResponse<String?> {
		var result = ResponseCode.ERROR
		var message = "Request Failed"
		var newToken: String? = null

		val cookies = req.cookies?.find { it.name == "token" }
		val token = cookies?.value

		if (token == null) {
			message = "Token does not exist."
		} else {
			try {
				val userId = jwtUtil.extractUsername(token)
				if (jwtUtil.validateToken(token, userId)) {
					newToken = jwtUtil.generateToken(userId)
					setJwtTokenCookie(res, newToken)

					result = ResponseCode.SUCCESS
					message = "SThe token has been renewed."
				} else {
					message = "The token is invalid."
				}
			} catch (e: Exception) {
				message = "An error occurred while renewing the token."
			}

		}
		return SingleResponse(
			result = result,
			message = message,
			data = newToken
		)
	}

	fun setJwtTokenCookie(response: HttpServletResponse, token: String) {
		val cookie = ResponseCookie.from("token", token)
			.httpOnly(false)  // HttpOnly 설정, 클라이언트에서 JS로 접근 불가
			.secure(false)   // HTTPS에서는 true로 설정 (지금은 HTTP이므로 false)
			.path("/")       // 모든 경로에서 쿠키가 유효
			.maxAge(60 * 60) // 쿠키 유효 시간: 1시간 (60 * 60초)
			.build()

		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString())
	}

	@PostMapping("/check-uid")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "아이디 중복 확인", description = "회원 아이디 중복 유무를 확인합니다.")
	fun checkDuplicateUid(@RequestBody req: UserCheckDuplicateUidRequest): SingleResponse<UserCheckDuplicateUidResponse?> {
		var result = ResponseCode.ERROR
		var message = "Request Failed"

		val response = userService.checkDuplicateUid(req)
		response?.let {
			result = ResponseCode.SUCCESS
			message = "ID is available"
		}

		return SingleResponse(
			result = result,
			message = message,
			data = response
		)
	}

}