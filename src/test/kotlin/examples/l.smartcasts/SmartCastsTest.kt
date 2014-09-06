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

        //we are sure x is String
        val y = x as String

        assertEquals(x, y)
    }
}