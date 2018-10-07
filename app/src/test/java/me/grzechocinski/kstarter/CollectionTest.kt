package me.grzechocinski.kstarter

import com.googlecode.catchexception.apis.BDDCatchException.`when`
import com.googlecode.catchexception.apis.BDDCatchException.caughtException
import com.memoizr.assertk.expect
import org.assertj.core.api.BDDAssertions.then
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
  fun `should iterate with pair of index and value`() {
    val grizabellas = listOf("Elaine", "Mamie", "Betty", "Leona")

    val starring: Array<String?> = arrayOfNulls(grizabellas.size)

    for ((index, value) in grizabellas.withIndex()) {
      starring[index] = "${index+1}) $value"
    }

    expect that starring[0] isEqualTo "1) Elaine"
    expect that starring[1] isEqualTo "2) Mamie"
    expect that starring[2] isEqualTo "3) Betty"
    expect that starring[3] isEqualTo "4) Leona"
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

  @Test
  fun `should find existing collection member with when keyword`() {
    val androidSweets = listOf("Lollipop", "Marshmallow", "Nougat", "Oreo")
    val result = when {
      "Nougat" in androidSweets -> "Nougat is here!"
      else -> throw IllegalStateException("Sould find 'Nougat' in array")
    }

    expect that result isEqualTo "Nougat is here!"
  }

  @Suppress("ReplaceGetOrSet")
  @Test
  fun `should access map entries in different ways`(){
    val nameToAge = mapOf("John" to 34)

    expect that nameToAge["John"]?.plus(5) isEqualTo 39
    expect that nameToAge["Mary"]?.plus(5) _is null

    expect that nameToAge.get("John")?.plus(5) isEqualTo 39
    expect that nameToAge.get("Mary")?.plus(5) _is null

    //Since Kotlin 1.1 - non nullable type
    expect that nameToAge.getValue("John") isEqualTo 34

    `when` { nameToAge.getValue("Mary") }
    then(caughtException())
      .isExactlyInstanceOf(NoSuchElementException::class.java)
      .hasMessage("Key Mary is missing in the map.")

  }
}
