package io.github.databob.unit

import io.github.databob.Databob
import org.junit.Test
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.test.assertTrue

class DatabobTest {
    val default = Databob()

    @Test
    fun support_primitives() {

        assertTrue(default.mk(Boolean::class) is Boolean)
        assertTrue(default.mk(Byte::class) is Byte)
        assertTrue(default.mk(String::class) is String)
        assertTrue(default.mk(Char::class) is Char)

        assertTrue(default.mk(Int::class) is Int)
        assertTrue(default.mk(Short::class) is Short)
        assertTrue(default.mk(Long::class) is Long)
        assertTrue(default.mk(BigInteger::class) is BigInteger)

        assertTrue(default.mk(Double::class) is Double)
        assertTrue(default.mk(Float::class) is Float)
        assertTrue(default.mk(BigDecimal::class) is BigDecimal)

        assertTrue(default.mk(Exception::class) is Exception)
        assertTrue(default.mk(RuntimeException::class) is RuntimeException)
    }

    @Test
    fun support_common_collections() {
        assertTrue(default.mk(List::class) is List)
        assertTrue(default.mk(Map::class) is Map)
        assertTrue(default.mk(Set::class) is Set)
    }

    data class Bob(val s: String, val num : Int)
    data class Rita(val v: Bob)
    data class Sue(val others: List<Rita>)

    @Test
    fun support_data_classses() {

        val mk = default.mk(Sue::class)
        assertTrue(mk is Sue)
        assertTrue(mk.others is List)
        assertTrue(mk.others[0] is Rita)
        assertTrue(mk.others[0].v is Bob)
        assertTrue(mk.others[0].v.s is String)
        assertTrue(mk.others[0].v.num is Int)
    }
}
