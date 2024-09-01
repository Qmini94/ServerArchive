package com.example.serverarchive.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
  @Bean
  fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
    val httpBasic = http
      .authorizeHttpRequests { authz ->
        authz.anyRequest().permitAll()
      }
      .csrf { it.disable() }
      .formLogin { it.disable() }
      .httpBasic { it.disable() }
    return http.build()
  }
}