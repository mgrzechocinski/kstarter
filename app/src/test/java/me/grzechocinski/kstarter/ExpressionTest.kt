package me.grzechocinski.kstarter

import com.memoizr.assertk.AbstractAssertBuilder
import com.memoizr.assertk.expect
import com.memoizr.assertk.isInstance
import org.junit.Test

class ExpressionTest {

  @Test
  fun `should evaluate when expression`() {
    fun describe(obj: Any): String = when (obj) {
      1 -> "One"
      "Hello" -> "Greeting"
      is Long -> "Long"
      !is String -> "Not a string"
      else -> "Unknown"
    }

    expect that describe(1) isEqualTo "One"
    expect that describe("Hello") isEqualTo "Greeting"
    expect that describe(1000L) isEqualTo "Long"
    expect that describe(2) isEqualTo "Not a string"
    expect that describe("other") isEqualTo "Unknown"
  }

  @Test
  fun `should return when statemant`() {
    fun transform(color: String): Int {
      return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
      }
    }

    expect that transform("Red") isEqualTo 0
    expect that transform("Green") isEqualTo 1
    expect that transform("Blue") isEqualTo 2

    expect thatThrownBy {
      transform("Yellow")
    } isInstance AbstractAssertBuilder.InstanceMatcher<IllegalArgumentException>()
    expect thatThrownBy { transform("Yellow") } hasMessage "Invalid color param value"
  }
}
