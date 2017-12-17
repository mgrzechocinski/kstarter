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

  @Suppress("UNUSED_EXPRESSION")
  @Test
  fun `should show the difference between let, also, apply, with, run`() {

    val dto = DTO("John", "Brown")

    //LET
    val resultOfLet = dto.let {
      expect that it.fullNameFirstName() isEqualTo "John Brown"
      expect that it.fullNameFirstSurname() isEqualTo "Brown John"
      "result of let"
    }
    expect that resultOfLet isEqualTo "result of let"

    //LET
    val resultOfAlso = dto.also {
      expect that it.fullNameFirstName() isEqualTo "John Brown"
      expect that it.fullNameFirstSurname() isEqualTo "Brown John"
      "result of also"
    }
    expect that resultOfAlso isEqualTo dto

    //APPLY
    val resultOfApply = dto.apply {
      expect that this.fullNameFirstName() isEqualTo "John Brown"
      expect that this.fullNameFirstSurname() isEqualTo "Brown John"
      "result of apply"
    }
    expect that resultOfApply isEqualTo dto

    //WITH
    val resultOfWith = with(dto) {
      expect that this.fullNameFirstName() isEqualTo "John Brown"
      expect that this.fullNameFirstSurname() isEqualTo "Brown John"
      "result of with"
    }
    expect that resultOfWith isEqualTo "result of with"

    // RUN (roughly same as with() but as extension. More: https://stackoverflow .com/questions/38501764/difference-between-with-and-run-in-kotlin)
    val resultOfRun = dto.run {
      expect that this.fullNameFirstName() isEqualTo "John Brown"
      expect that this.fullNameFirstSurname() isEqualTo "Brown John"
      "result of run"
    }
    expect that resultOfRun isEqualTo "result of run"
  }
}
