package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.assertj.core.api.Fail
import org.junit.Test

class NullabilityTest {

  @Test
  fun `should check for nulls and do smart casting`() {

    fun analyzeProduct(arg1: String, arg2: String): Any {
      val x = arg1.toIntOrNull()
      val y = arg2.toIntOrNull()

      return if (x != null && y != null) x * y else "either '$arg1' or '$arg2' is not a number"
    }

    expect that analyzeProduct("6", "7") isEqualTo 42
    expect that analyzeProduct("a", "7") isEqualTo "either 'a' or '7' is not a number"
    expect that analyzeProduct("a", "b") isEqualTo "either 'a' or 'b' is not a number"
  }

  @Test
  fun `should use elvis operator for nullable types`() {

    val nullable: String? = null
    expect that nullable?.substring(1, 2) _is null

    expect that (nullable?.substring(1, 2) ?: "empty") isEqualTo "empty"

    val data: Int? = null

    data?.let {
      Fail.fail("Should not be called, since parent is null")
    }
  }
}