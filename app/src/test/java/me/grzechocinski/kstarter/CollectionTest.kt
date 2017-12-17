package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class CollectionTest {

  @Test
  fun `should iterate with indices`() {
    val grizabellas = listOf("Elaine", "Mamie", "Betty", "Leona")

    val expectations = mapOf(0 to "Elaine", 1 to "Mamie", 2 to "Betty", 3 to "Leona")

    for (index in grizabellas.indices) {
      expect that grizabellas[index] isEqualTo expectations.getValue(index)
    }
  }

  @Test
  fun `should traverse map`() {

    val map = mapOf("1" to 1, "2" to 2, "3" to 3)

    for ((k, v) in map) {
      expect that k.toIntOrNull() isEqualTo v
    }
  }

  @Test
  fun `should collection be immutable`() {
    val list = listOf(1, 2, 3, 4, 5)

    val sliced = list.slice(0..1)
    expect that sliced containsOnly listOf(1, 2)
    expect that sliced isNotEqualTo list
  }

  @Test
  fun `should deny to call set on immutable map and allow to change mutable map entries only`() {
    val immutableMap = mapOf("a" to 1, "b" to 2, "c" to 3)
    //immutableMap["a"] = 100 //compilation error
    expect that immutableMap["a"] isEqualTo 1

    val mutableMap = HashMap(immutableMap)
    expect that mutableMap["a"] isEqualTo 1
    mutableMap["a"] = 100
    expect that mutableMap["a"] isEqualTo 100
  }


}
