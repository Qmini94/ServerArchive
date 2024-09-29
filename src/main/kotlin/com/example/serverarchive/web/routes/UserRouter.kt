package com.example.serverarchive.web.routes

import JwtUtil
import com.example.serverarchive.api.request.user.UserListRequest
import com.example.serverarchive.service.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import java.net.URI

@Configuration
class UserRouter(
	private val jwtUtil: JwtUtil,
	private val userService: UserService
) {

	@Bean
	fun userRoutes() = router {
		("/user").nest {
			GET("/create", ::viewCreatePage)
			GET("/list", ::viewUserListPage)
			GET("/login", ::viewLoginPage)
		}
	}

	fun viewCreatePage(req: ServerRequest): ServerResponse {
		val data = mapOf(
			"message" to "Register New User",
			"pageTitle" to "회원가입"
		)
		return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("manager/user/create", data)
	}

	fun viewUserListPage(req: ServerRequest): ServerResponse {
		val defaultPage = 1
		val defaultSize = 10

		val page = req.param("page").map { it.toIntOrNull() ?: defaultPage }.orElse(defaultPage)
		val size = req.param("size").map { it.toIntOrNull() ?: defaultSize }.orElse(defaultSize)
		val searchKey = req.param("searchKey").orElse("")
		val keyword = req.param("keyword").orElse(null)

		val userListRequest = UserListRequest(
			page = page,
			size = size,
			searchKey = searchKey,
			keyword = keyword
		)

		val users = userService.getUserList(userListRequest)

		// TODO :: 유지보수 차원에서 적합할까?
		val searchOptions = listOf(
			mapOf("value" to "userId", "label" to "아이디"),
			mapOf("value" to "userName", "label" to "회원 이름"),
			mapOf("value" to "role", "label" to "권한")
		)

		val data = mapOf(
			"pageTitle" to "회원목록",
			"users" to users,
			"options" to searchOptions,
		)

		return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("manager/user/list", data)
	}

	fun viewLoginPage(req: ServerRequest): ServerResponse {
		handleTokenRedirection(req, jwtUtil)?.let { return it }
		val data = mapOf("pageTitle" to "로그인")
		return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("client/user/login", data)
	}

	/*로그인이 된 경우 로그인 페이지로 이동 못하도록*/
	fun handleTokenRedirection(req: ServerRequest, jwtUtil: JwtUtil): ServerResponse? {
		val cookies = req.headers().asHttpHeaders().getFirst("Cookie")
		val token = cookies?.split(";")?.find { it.trim().startsWith("token=") }?.substringAfter("token=")

		println("Cookies: $cookies")
		println("Extracted token from cookies: $token")

		if (token != null) {
			val userId = jwtUtil.extractUsername(token)
			if (jwtUtil.validateToken(token, userId)) {
				return ServerResponse.temporaryRedirect(URI("/server/list")).build()
			}
		}
		return null
	}

}