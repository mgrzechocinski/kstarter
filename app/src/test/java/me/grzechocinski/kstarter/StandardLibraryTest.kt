package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class StandardLibraryTest {

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
}
