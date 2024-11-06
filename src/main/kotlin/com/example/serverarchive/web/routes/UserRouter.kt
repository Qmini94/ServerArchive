package com.example.serverarchive.web.routes

import JwtUtil
import com.example.serverarchive.service.user.UserService
import com.example.serverarchive.util.Logger
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
	private val logger by Logger()

	@Bean
	fun userRoutes() = router {
		("/user").nest {
			GET("/create", ::viewCreatePage)
			GET("/list", ::viewUserListPage)
			GET("/login", ::viewLoginPage)
			GET("/update/{idx}", ::viewUpdatePage)
		}
	}

	fun viewCreatePage(req: ServerRequest): ServerResponse {
		val data = mapOf(
			"message" to "Create Account",
			"pageTitle" to "회원가입"
		)
		return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("manager/user/create", data)
	}

	fun viewUpdatePage(req: ServerRequest): ServerResponse {
		val idx = req.pathVariable("idx").toInt()
		val users = userService.getUserByIdx(idx)

		println(users)

		val data = mapOf(
			"message" to "Modify User Info",
			"pageTitle" to "회원정보"
		)

		return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("manager/user/create", data)

	}

	fun viewUserListPage(req: ServerRequest): ServerResponse {
		val data = mapOf(
			"pageTitle" to "회원목록",
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