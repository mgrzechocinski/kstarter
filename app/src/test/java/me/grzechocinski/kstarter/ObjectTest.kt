package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class ObjectTest {

  @Test
  fun `should be a singleton`() {

    val r1 = ResourceObject
    val r2 = ResourceObject

    expect that r1 isEqualTo r2
  }

  object ResourceObject {
    val name = "Name"
  }
}
