package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import org.junit.Test

class KotlinBasicTypesTest {

    @Test fun `should have different references due to boxing`() {
        val a: Int = 10000
        expect that (a === a) isEqualTo true

        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        expect that (boxedA === anotherBoxedA) isEqualTo false
    }

    @Test fun `should be equal after boxing`() {
        val a: Int = 10000
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        expect that (boxedA == anotherBoxedA) isEqualTo true
    }
}