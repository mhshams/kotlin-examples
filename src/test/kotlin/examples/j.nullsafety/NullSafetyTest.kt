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

        //smart null checking. within if block, we are asking for 'b.length', because we are sure b is not null
        if (b != null) assertEquals(4, b.length())

        assertEquals(-1, if (a != null) a.length() else -1)
        assertEquals(4, if (b != null) b.length() else -1)

        // '?' is used for null safety too.
        assertEquals(null, a?.length())
        assertEquals(4, b?.length())

        assertEquals(-1, a?.length() ?: -1)
        assertEquals(4, b?.length() ?: -1)

        //if you want to ignore the nullability, when you are sure its not null. (useful in working with java libs)
        assertEquals(4, b!!.length())
    }

    test fun `safe cast`() {
        val a: Any? = 4
        val b: Any? = null

        //safe cast 'as?'
        val x: Int? = a as? Int
        val y: Int? = b as? Int

        assertEquals(4, x)
        assertEquals(null, y)
    }
}