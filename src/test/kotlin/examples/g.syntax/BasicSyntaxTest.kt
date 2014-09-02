package examples.basic

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 *
 * @author me
 *
 */

class BasicSyntaxTest {

    test fun `if (and else) block returns value `() {

        fun max(a: Int, b: Int) = if (a > b) a else b

        assertEquals(5, max(2, 5))
        assertEquals(3, max(3, 3))
    }

    test fun `when is used for pattern matching`() {

        fun cases(obj: Any): String {
            when (obj) {
                1 -> return "One"
                "Hello" -> return "Greeting"
                is Long -> return "Long"
                !is String -> return "Not a string"
                else -> return "Unknown"
            }
        }

        assertEquals("One", cases(1))
        assertEquals("Greeting", cases("Hello"))
        val l: Long = 1
        assertEquals("Long", cases(l))
        assertEquals("Not a string", cases(true))
        assertEquals("Unknown", cases("OK"))
    }

    test fun `range is new type in kotlin`() {
        val x = 3
        if (x in 1..5)
            assertTrue(true)

        val names = array("ok", "test", "best")

        if (x !in 0..names.lastIndex)
            assertTrue(true)

        for (i in 0..10)
            assertTrue(i >= 0 && i <= 10)
    }

    test fun `a functional test`() {
        val names = array("ok", "test", "Arak", "game", "Azad", "Ali", "kia", "ab")

        val result = names filter { it.startsWith("A") } sortBy { it } map { it.toUpperCase() }

        assertEquals(3, result.size)
        assertEquals("ALI", result[0])
        assertEquals("ARAK", result[1])
        assertEquals("AZAD", result[2])
    }
}


