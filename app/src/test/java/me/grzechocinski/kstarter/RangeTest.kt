package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class RangeTest {

  @Test
  fun `should evaluate range first`() {
    val x = 10
    val y = 9
    expect that (x in 1..y + 1) isEqualTo true

    val list = listOf("a", "b", "c")
    expect that (-1 !in 0..list.lastIndex) describedAs "Index should be out of range" _is true
  }

  @Test
  fun `should iterate over range inclusively`() {
    var even = ""
    for (x in 0..5) {
      even += x
    }
    expect that even isEqualTo "012345"
  }

  @Test
  fun `should iterate over range with steps`() {
    for (i in 0..10 step 2) {
      expect that i % 2 isEqualTo 0
    }
  }

  @Test fun `should iterate in reverse order with down to keyword`() {
    var tenReversed = ""
    for (x in 10 downTo 0){
      tenReversed += x
    }
    expect that tenReversed isEqualTo "109876543210"
  }

  @Test
  fun `should iterate range exclusively with until keyword`() {
    for (i in 1 until 100) {
      expect that i isNotEqualTo 100
    }
  }
}
