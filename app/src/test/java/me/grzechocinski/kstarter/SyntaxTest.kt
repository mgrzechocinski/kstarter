package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class SyntaxTest{

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
  fun `should have default values`() {

    // TODO [mgrzechocinski on 13/05/2017]: Why it doesn't infer type, whereas inline version does?

    /*fun foo(a: Int = 5, b: Int){
        return a + b
    }*/

    fun foo(a: Int = 5, b: Int) = a + b

    expect that foo(b = 10) isEqualTo 15
  }
}
