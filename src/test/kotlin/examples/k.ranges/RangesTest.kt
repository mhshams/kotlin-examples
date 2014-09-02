package examples.ranges

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/**
 *
 * @author me
 *
 */
class RangesTest {

    test fun `ranges in kotlin`() {
        val i = 2
        assertTrue(i in 1..3)
        assertTrue(i in 2..3)
        assertTrue(i in 1..2)
        assertTrue(i in 2..2)

        val x = 4.5
        assertFalse(x in 1.0..3.0)
        assertTrue(x !in 1.0..3.0)

        val str = "isle"
        assertTrue(str in "island".."isle")
    }

    test fun `more about ranges in kotlin`() {
        val x = (1..4) map { "$it" } reduce {(p: String, q: String) -> "$p,$q" }
        assertEquals("1,2,3,4", x)

        val list = (4..1) map { "$it" }
        assertTrue(list.isEmpty())

        val z = (1.0..2.0) map { "$it" } reduce {(p: String, q: String) -> "$p,$q" }
        assertEquals("1.0,2.0", z)

        val xx = (4 downTo 1) map { "$it" } reduce {(p: String, q: String) -> "$p,$q" }
        assertEquals("4,3,2,1", xx)

        val yy = (1..4 step 2) map { "$it" } reduce {(p: String, q: String) -> "$p,$q" }
        assertEquals("1,3", yy)

        val zz = (4 downTo 1 step 2) map { "$it" } reduce {(p: String, q: String) -> "$p,$q" }
        assertEquals("4,2", zz)

        val w = (1.0..1.8 step 0.3) map { "$it" } reduce {(p: String, q: String) -> "$p,$q" }
        assertEquals("1.0,1.3,1.6", w)
    }
}