package com.example.serverarchive.config

import JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {
	@Bean
	fun jwtUtil(): JwtUtil {
		return JwtUtil()  // JwtUtil 빈을 수동으로 등록
	}
}