package interview

import com.memoizr.assertk.expect
import me.grzechocinski.kstarter.domain.Date
import org.junit.Test

class DateTest {

  @Test
  fun `should check date in range by using operator fun`() {
    //given
    val yearStart = Date("2018-01-01")
    val yearEnd = Date("2018-12-31")

    val yearMiddle = Date("2018-06-01")

    //when
    val isInMiddle: Boolean = yearMiddle in yearStart..yearEnd

    //then
    expect that isInMiddle isEqualTo true
  }
}