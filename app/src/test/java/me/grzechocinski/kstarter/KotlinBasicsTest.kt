package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class KotlinBasicsTest {

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
  fun `should play with ranges`() {
    val x = 10
    val y = 9
    expect that (x in 1..y + 1) isEqualTo true

    val list = listOf("a", "b", "c")
    expect that (-1 !in 0..list.lastIndex) describedAs "Index should be out of range" _is true
  }

  @Test
  fun `should iterate over range with steps`() {
    for (i in 0..10 step 2) {
      expect that i % 2 isEqualTo 0
    }
  }

  @Test
  fun `should make fun with streams`() {

    val items = listOf("orange", "kiwi", "banana", "mango", "melon")

    val mFruits = items
      .filter { fruitName -> fruitName.startsWith("m") }
      .map { fruitName ->
        when (fruitName) {
          "melon" -> "Mango"
          "mango" -> "Melon"
          else -> "unknown!"
        }
      }
      .toList()

    expect that mFruits containsOnly listOf("Mango", "Melon")
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
}