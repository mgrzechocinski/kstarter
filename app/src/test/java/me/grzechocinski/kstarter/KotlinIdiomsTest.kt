package me.grzechocinski.kstarter

import com.memoizr.assertk.AbstractAssertBuilder
import com.memoizr.assertk.expect
import com.memoizr.assertk.isInstance
import org.assertj.core.api.Fail
import org.junit.Ignore
import org.junit.Test

class KotlinIdiomsTest {

  data class Customer(val name: String, val email: String)

  @Test
  fun `should create DTO`() {

    val john = Customer("John", "john@example.com")
    val otherJohn = Customer("John", "john@example.com")

    expect that john isEqualTo otherJohn
    expect that john.name isEqualTo "John"
    expect that john.email isEqualTo "john@example.com"
    expect that john.toString() isEqualTo "Customer(name=John, email=john@example.com)"

    val mary = john.copy(name = "Mary")

    expect that mary isNotEqualTo john
    expect that mary.email isEqualTo john.email
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

  @Test
  fun `should lambda parameter be ommitable`() {

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
  fun `should traverse map`() {

    val map = mapOf("1" to 1, "2" to 2, "3" to 3)

    for ((k, v) in map) {
      expect that k.toIntOrNull() isEqualTo v
    }
  }

  @Test
  fun `should play with ranges`() {
    for (i in 1 until 100) {
      expect that i isNotEqualTo 100
    }

    val numbers = listOf(1, 2, 3, 4, 5)
    for (i in 5..1) {
      expect that numbers contains listOf(i)
    }
    for (i in 5 downTo 1) {
      expect that numbers contains listOf(i)
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
  fun `should deny to call set on immutable map`() {
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)

    //map["a"] = 1

    expect that map["a"] isEqualTo 1
  }

  @Test
  fun `should init property lazily`() {

    val lazy by lazy {
      "layzvalue"
    }

    expect that lazy isEqualTo "layzvalue"
  }

  @Test
  fun `should use extension function`() {

    fun Int.negative() = -this

    expect that 1.negative() isEqualTo -1
  }

  @Test
  fun `should be a singleton`() {

    val r1 = ResourceObject
    val r2 = ResourceObject

    expect that r1 isEqualTo r2
  }

  object ResourceObject {
    val name = "Name"
  }

  @Test
  fun `should play with null safety`() {

    val nullable: String? = null
    expect that (nullable?.substring(1, 2) == null) isEqualTo true

    expect that (nullable?.substring(1, 2) ?: "empty") isEqualTo "empty"

    val data: Int? = null

    data?.let {
      Fail.fail("Should not be called, since parent is null")
    }
  }

  @Test
  fun `should return when statemant`() {
    fun transform(color: String): Int {
      return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
      }
    }

    expect that transform("Red") isEqualTo 0
    expect that transform("Green") isEqualTo 1
    expect that transform("Blue") isEqualTo 2

    expect thatThrownBy {
      transform("Yellow")
    } isInstance AbstractAssertBuilder.InstanceMatcher<IllegalArgumentException>()
    expect thatThrownBy { transform("Yellow") } hasMessage "Invalid color param value"
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

  data class DTO(val name: String, val surname: String) {
    fun fullNameFirstName() = "$name $surname"
    fun fullNameFirstSurname() = "$surname $name"
  }

  @Ignore
  @Test
  fun `should run inline fun`() {
    TODO("Too late at night. Not implemented yet")
  }

  @Test
  fun `should consume nullable Boolean`() {
    val value: Boolean? = null
    if (value == true) {
      Fail.fail("Should not enter this path. Boolean value can't be true")
    } else {
      expect that (value == null) isEqualTo true
    }
  }


}
