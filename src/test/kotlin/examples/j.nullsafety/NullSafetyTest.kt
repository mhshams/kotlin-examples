package examples.nullsafety

import org.junit.Test as test
import kotlin.test.assertEquals

/**
 *
 * @author me
 *
 */
class NullSafetyTest {
    test fun `null safety check`() {
        val a: String? = null
        val b: String? = "test"

        if (b != null) assertEquals(4, b.length())

        assertEquals(-1, if (a != null) a.length() else -1)
        assertEquals(4, if (b != null) b.length() else -1)

        assertEquals(null, a?.length())
        assertEquals(4, b?.length())

        assertEquals(-1, a?.length() ?: -1)
        assertEquals(4, b?.length() ?: -1)

        assertEquals(4, b!!.length())
    }

    test fun `safe cast`() {
        val a: Any? = 4
        val b: Any? = null

        val x: Int? = a as? Int
        val y: Int? = b as? Int

        assertEquals(4, x)
        assertEquals(null, y)
    }
}