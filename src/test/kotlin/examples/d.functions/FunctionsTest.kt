package examples.functions

import org.junit.Test as test
import org.junit.Ignore as ignore
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.util.ArrayList

/**
 *
 * @author me
 *
 */
class FunctionsTest {

    test fun `collect palindromes`() {
        /**
         * Collects palindrome names in the given list
         */
        fun collect(names: Array<String>): List<String> {

            /**
             * Inner functions to check if the given name is palindrome
             */
            fun palindrome(name: String): Boolean {
                for (i in 0..name.length / 2) {
                    if (name[i] != name[name.length - 1 - i])
                        return false
                }
                return true
            }

            return names filter { palindrome(it) }
        }

        //given
        val names = array("mam", "chech", "a", "foo", "bar", "madam")

        //when
        val results = collect(names)

        //then
        assertEquals(3, results.size)
        assertTrue("mam" in results)
        assertTrue("a" in results)
        assertTrue("madam" in results)
    }

    ignore("compiler error - maybe a bug")
    test fun `singleton array`() {

        /**
         * A generic function to generate a singleton array
         */
        fun singletonArray<T>(item: T): Array<T> {
            //return array(item)
            throw RuntimeException("a bug?")
        }

        //when
        val a = singletonArray("OK")

        //then
        assertTrue(a is Array<String>)
        assertTrue(a.size == 1)
        assertEquals("OK", a[0])
    }

    test fun `singleton list`() {

        /**
         * a generic function to generate singleton list.
         */
        fun singletonList<T>(item: T): List<T> {
            return listOf(item) // just to keep it simple ;)
        }

        //when
        val a = singletonList("OK")

        //then
        assertTrue(a is List<String>)
        assertTrue(a.size == 1)
        assertEquals("OK", a[0])
    }

    test fun `a practise to convert varargs to list`() {

        /**
         * Converts the given parameters to a list.
         */
        fun convert<T>(vararg params: T): List<T> {
            val result = ArrayList<T>()

            params forEach { result.add(it) }

            return result
        }

        //when
        val list = convert("A", "B", "C")

        //then
        assertTrue(list is List<String>)
        assertEquals(3, list.size)
        assertTrue("A" in list)
        assertTrue("B" in list)
        assertTrue("C" in list)
    }

    test fun `functions with default args`() {

        /**
         * a function with default value for its args.
         */
        fun substring(s: String, from: Int = 0, too: Int = -1): String {
            val f = if (from < 0) 0 else from
            val t = if (too < 0 || too > s.size) s.size else too

            return s.substring(f, t)
        }

        //expect
        assertEquals("HELLO", substring("HELLO"))
        assertEquals("HELLO", substring("HELLO", 0))
        assertEquals("HELLO", substring("HELLO", 0, 110))

        assertEquals("ELLO", substring("HELLO", 1))
        assertEquals("LL", substring("HELLO", 2, 4))
    }

    test fun `calling function with named args`() {

        fun substring(s: String, from: Int = 0, too: Int = -1): String {
            val f = if (from < 0) 0 else from
            val t = if (too < 0 || too > s.size) s.size else too

            return s.substring(f, t)
        }

        // calling function with named args
        assertEquals("HEL", substring(s = "HELLO", too = 3))
        assertEquals("LO", substring(s = "HELLO", from = 3))
        assertEquals("LO", substring("HELLO", too = 5, from = 3))
    }

    test fun `infix call`() {
        assertEquals((3 + 5) * 2, 3 plus 5 times 2)
        assertEquals(3 + 5 * 2, 5 times 2 plus 3)
    }

    test fun `chaining functions`() {
        //given
        val strings = array("ok", "Kotlin", "ok i got it", "then wahtsfs")

        //when
        val result = strings filter { it.length == 6 } sortBy { it } map { it.toUpperCase() }

        //then
        assertEquals(1, result.size)
        assertEquals("KOTLIN", result[0])
    }
}

