package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import com.memoizr.assertk.of
import org.assertj.core.api.Fail
import org.junit.Test

class TypesTest {

  @Test
  fun `should have different references due to boxing`() {
    val a: Int = 10000
    expect that (a === a) isEqualTo true

    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    expect that (boxedA === anotherBoxedA) isEqualTo false
  }

  @Test
  fun `should be equal after boxing`() {
    val a: Int = 10000
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    expect that (boxedA == anotherBoxedA) isEqualTo true
  }

  @Suppress("JoinDeclarationAndAssignment")
  @Test
  fun `should initialize val in next line`() {
    val c: Int
    val b: String
    c = 12
    b = "Test"

    expect that c isEqualTo 12
    expect that b isEqualTo "Test"
  }

  @Test
  fun `should init property lazily`() {

    val lazy by lazy {
      "layzvalue"
    }
    expect that lazy isEqualTo "layzvalue"
  }

  @Test
  fun `should be long`() {
    val l = 1L + 3

    expect that l isInstance of<Long>()
  }

  @Test
  fun `should consume nullable Boolean`() {
    val value: Boolean? = null
    if (value == true) {
      Fail.fail("Should not enter this path. Boolean value can't be true")
    } else {
      expect that (value == null) isEqualTo true
    }
  }
}