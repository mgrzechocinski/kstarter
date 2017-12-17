package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class SyntaxTest {

  @Test
  fun `should lambda parameter be omitted`() {
    val list = listOf(1, 2, 3, 4)

    val filtered1 = list.filter { i -> i > 2 }
    val filtered2 = list.filter { it > 2 }

    expect that filtered1 isEqualTo filtered2
  }

  @Test
  fun `should interpolate string`() {
    val x = "Matt"

    expect that "Hi, $x!" isEqualTo "Hi, Matt!"
  }

  @Test
  fun `should trim margin in raw string by |`() {

    val multiline = """
          multiline
          first
          second
          third
        """
    expect that multiline.contains(" ") isEqualTo true

    val multilineTrimmed = """
          |multilineTrimmed
          |first
          |second
          |third
        """.trimMargin()
    expect that multilineTrimmed.contains(" ") isEqualTo false

    //print(multiline)
    //print(multilineTrimmed)
  }

  @Test
  fun `should user dollar character in raw string`() {
    val rawString = """Dollar is: ${'$'}"""
    expect that rawString isEqualTo "Dollar is: $"
  }

  @Test
  fun `should have default values`() {

    // TODO [mgrzechocinski on 13/05/2017]: Why it doesn't infer type, whereas inline version does?

    /*fun foo(a: Int = 5, b: Int){
        return a + b
    }*/

    fun foo(a: Int = 5, b: Int) = a + b

    expect that foo(b = 10) isEqualTo 15
  }

  @Suppress("LoopToCallChain")
  @Test
  fun `should exit labeled loop`() {
    var result = ""

    main@ for (x in 0..2) {

      result += "${x}_"

      for (y in 10..20) {
        if (y == 15) {
          break@main
        }
        result += "${y}|"
      }
    }
    expect that result isEqualTo "0_10|11|12|13|14|"
  }

  @Test
  fun `should return from outer function`() {
    fun foo(): String {
      arrayOf(1, 2, 3).forEach {
        if (it == 1) {
          return "Found one!"
        }
      }
      return "Could not find one!"
    }

    expect that foo() isEqualTo "Found one!"
  }

  @Test
  fun `should return from lambda`() {
    fun foo(): String {
      arrayOf(1, 2, 3).forEach lambda@{
        if (it == 1) {
          return@lambda
        }
      }
      return "Default value!"
    }

    expect that foo() isEqualTo "Default value!"
  }

  /**
   * How to return from lambda?
   */
  /**
  @Test
  fun `should return from lambda with result`() {
    fun foo(): String {
      var result : String = arrayOf("John", "Mary").last() mylabel@{
        if(it == "John") {
          return@mylabel "John is the best!"
        }
        true
      }
      return result +" Really!"
    }

    expect that foo() isEqualTo "John is the best! Really!"
  }
  */
}
