package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class DataClassTest {

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
}