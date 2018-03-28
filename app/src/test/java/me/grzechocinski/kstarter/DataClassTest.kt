package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class DataClassTest {

  data class Customer(val name: String, val email: String)

  @Test
  fun `should create DTO with utility methods like equals, hashCode and toString`() {

    val john = Customer("John", "john@example.com")
    val otherJohn = Customer("John", "john@example.com")

    expect that john isEqualTo otherJohn
    expect that john.hashCode() isEqualTo otherJohn.hashCode()
    expect that john.toString() isEqualTo "Customer(name=John, email=john@example.com)"

    expect that john.name isEqualTo "John"
    expect that john.email isEqualTo "john@example.com"
  }

  @Test fun `should copy data class with only one parameter changes`() {
    val john = Customer("John", "john@example.com")

    val mary = john.copy(name = "Mary")

    expect that mary isNotEqualTo john
    expect that john.name isEqualTo "John"
    expect that mary.name isEqualTo "Mary"
    expect that mary.email isEqualTo john.email
  }

  data class JohnByDefault(val name: String = "John", val email: String = "john@example.com")

  @Test fun `should init data class with default parameters`() {

    val john = JohnByDefault(email = "john@anotherdomain.com")

    expect that john.name isEqualTo "John"
    expect that john.email isEqualTo "john@anotherdomain.com"
  }
}
