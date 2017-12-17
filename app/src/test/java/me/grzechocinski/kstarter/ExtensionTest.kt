package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class ExtensionTest {

  @Test
  fun `should use extension function`() {

    fun Int.negative() = -this

    expect that 1.negative() isEqualTo -1
  }

  data class DTO(val name: String, val surname: String) {
    fun fullNameFirstName() = "$name $surname"
    fun fullNameFirstSurname() = "$surname $name"
  }

  @Test
  fun `should use with keyword to call one object multiple time`() {

    val dto = DTO("John", "Brown")

    with(dto) {
      expect that this.fullNameFirstName() isEqualTo "John Brown"
      expect that this.fullNameFirstSurname() isEqualTo "Brown John"
    }

    dto.let {
      expect that it.fullNameFirstName() isEqualTo "John Brown"
      expect that it.fullNameFirstSurname() isEqualTo "Brown John"
    }

    dto.apply {
      expect that this.fullNameFirstName() isEqualTo "John Brown"
      expect that this.fullNameFirstSurname() isEqualTo "Brown John"
    }

    // TODO [mgrzechocinski on 13/05/2017]: So when should we use 'with' ?
  }
}
