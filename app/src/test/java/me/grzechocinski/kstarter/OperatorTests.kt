package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import me.grzechocinski.kstarter.domain.SystemDate
import org.junit.Test

class OperatorTests {

  @Test
  fun `should use fun operators to check if date is in range`() {
    //given
    val startDate = SystemDate("2018-01-01")
    val endDate = SystemDate("2018-12-31")

    val toBeChecked = SystemDate("2018-06-01")

    //when
    val inRange: Boolean = toBeChecked in startDate..endDate

    //then
    expect that inRange isEqualTo true
  }
}