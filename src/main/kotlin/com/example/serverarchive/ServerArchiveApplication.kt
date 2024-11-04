package com.example.serverarchive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
class ServerArchiveApplication

fun main(args: Array<String>) {
  runApplication<ServerArchiveApplication>(*args)
}
