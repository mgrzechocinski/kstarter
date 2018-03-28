package me.grzechocinski.kstarter

import com.googlecode.catchexception.apis.BDDCatchException.`when`
import com.googlecode.catchexception.apis.BDDCatchException.caughtException
import com.memoizr.assertk.expect
import org.assertj.core.api.BDDAssertions.then
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
  fun `should return when statement as a single-expression function`() {
    fun transform(color: String) = when (color) {
      "Red" -> 0
      "Green" -> 1
      "Blue" -> 2
      else -> throw IllegalArgumentException("Invalid color param value")
    }

    expect that transform("Red") isEqualTo 0
    expect that transform("Green") isEqualTo 1
    expect that transform("Blue") isEqualTo 2

    `when` { transform("Yellow") }
    then(caughtException())
      .isExactlyInstanceOf(IllegalArgumentException::class.java)
      .hasMessage("Invalid color param value")
  }
}
