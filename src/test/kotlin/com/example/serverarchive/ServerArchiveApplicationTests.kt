package com.example.serverarchive

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ServerArchiveApplicationTests {

  @Test
  fun testAddition() {
    val a = 2;
    val b = 3;
    val result = add(a, b)
    assertEquals(5, result)
  }

  private fun add(a: Int, b: Int): Int {
    return a + b
  }

}
