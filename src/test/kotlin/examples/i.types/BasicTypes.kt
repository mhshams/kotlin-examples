package examples.types

import org.junit.Test as test
import org.junit.Ignore as ignore
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertEquals

/**
 *
 * @author me
 *
 */
class BasicTypesTest {

    test fun `basic types`() {
        val a = 100
        val b = 100
        val i: Int = a
        val j: Int = b

        assertTrue(i == j)
        assertTrue(i.equals(j))
        assertTrue(i.identityEquals(j))

        assertTrue(a == b)
        assertTrue(a equals b)
        assertTrue(a identityEquals b)
    }

    test fun `convert from one type to another`() {
        val b: Byte = 1
        val i: Int = b.toInt()
        val c: Char = 'A'

        assertEquals(1, i)
        assertEquals(65, c.toInt())
    }

    test fun `bitwise operators`() {
        val a = 0b00000111
        val b = 0b00001110

        assertEquals(0b00000110, a and b)
        assertEquals(0b00001111, a or b)
        assertEquals(0b00001001, a xor b)

        assertEquals(-0b00001000, a.inv())
        assertEquals(0b00001111, b.inc())
    }

    ignore("another potentical bug?")
    test fun `identity equals`() {
        val tenThousand: Int = 10000
        assertTrue(tenThousand identityEquals tenThousand)

        val thousand: Int = 1000
        assertTrue(thousand identityEquals thousand)

        val hundred: Int = 100
        assertTrue(hundred identityEquals hundred)

        val ten: Int = 10
        assertTrue(ten identityEquals ten)

        val a = 10
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        assertTrue(boxedA identityEquals anotherBoxedA)
    }

    test fun `array index`() {
        val a = array(1, 3, 5, 0, 4, 2, 2, 2, 1)

        for (i in a.indices) {
            assertTrue(i >= 0 && i < a.size)
        }
    }

    test fun `when test`() {
        val x = 0
        when {
            x % 2 != 0 -> assertFalse(x % 2 == 0)
            x % 2 == 0 -> assertFalse(x % 2 != 0)
            // else is optional
            else -> assertTrue(true)
        }
    }

    test fun `additional checks`() {
        val ba: Byte = 255.toByte()
        val bb: Byte = 255.toByte()
        assertTrue(ba identityEquals ba)
        assertTrue(bb identityEquals bb)
        assertTrue(ba identityEquals bb)

        val sa = String("AB".getBytes())
        val sb = String("AB".getBytes())

        assertTrue(sa identityEquals sa)
        assertTrue(sb identityEquals sb)
        assertFalse(sa identityEquals sb)

        val ia: Int = ba.toInt()
        val ib: Int = bb.toInt()
        assertTrue(ia identityEquals ia)
        assertTrue(ib identityEquals ib)
        assertTrue(ia identityEquals ib)

        assertFalse(1 identityEquals null)
    }
}
