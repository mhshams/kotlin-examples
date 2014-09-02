package examples.smartcasts

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 *
 * @author me
 *
 */
class SmartCast {

    test fun `smart cast`() {

        fun demo(x: Any) {
            if (x is String) {
                assertEquals(x, x.toUpperCase().toLowerCase()) // x is automatically cast to String
            }
        }

        demo(1)
        demo("ok")
    }

    test fun `another smart cast`() {

        fun demo(x: Any) {
            when (x) {
                is Int -> assertTrue(0 < x)
                is String -> assertTrue(x.length > 0)
                is Array<Int> -> x forEach { assertTrue(it > 0) }
                else -> assertTrue(true)
            }
        }

        demo(1)
        demo("me")
        demo(array(1, 2, 3))
        demo(2.5)
    }

    test fun `unsafe cast`() {
        val x: Any = "ok"
        val y = x as String

        assertEquals(x, y)
    }

    test fun `type inference`() {

        fun castMe(k: String?, v: String): String {
            val m: MutableMap<String, String> = hashMapOf("one" to "1 value", "two" to "2 value")

            if (k != null) {
                m[k] = v
            }

            return if (k != null) m[k]!! else "NULL"
        }

        val k: String? = "ok"

        assertEquals("okk", castMe(k, "okk"))

        assertEquals("NULL", castMe(null, "okk"))
    }
}