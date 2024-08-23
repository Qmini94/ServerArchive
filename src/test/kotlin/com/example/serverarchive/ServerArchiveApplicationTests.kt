package com.example.serverarchive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ServerArchiveApplicationTests {

  @Test
  fun testAddition() {
    val result = add(2, 3)
    assertEquals(5, result)
  }

  private fun add(a: Int, b: Int): Int {
    return a + b
  }

}
