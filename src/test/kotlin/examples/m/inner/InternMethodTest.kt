package examples.m.inner

import org.junit.Test as test
import java.math.BigInteger
import java.security.SecureRandom
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 *
 * @author me
 *
 */

class InternMethodTest {

    test fun `extension function`() {
        assertEquals("Hello, Me!", "Me".hi())
        assertEquals("Hello, Foo!", "Foo".hi())
        assertEquals("Hello, Bar!", "Bar".hi())
    }

    test fun `a filtered hello`() {
        val names = array("Hi", "Food", "Foo", "Bard", "Go");
        val filteredNames = filteredHello(names)

        assertTrue(2 == filteredNames.size)
        assertEquals("Hello, FOOD!", filteredNames[0])
        assertEquals("Hello, BARD!", filteredNames[1])
    }

    test fun `generator`() {
        val n = 100
        val random = SecureRandom()

        (0..5).forEach {
            val time = timer {
                generate(n, { BigInteger(130, random).toString(32) })
            }

            assertTrue(time > 0)
        }
    }
}


/**
 * An extension function for String class
 */
fun String.hi(): String = "Hello, $this!"

/**
 * A functional fun
 */
fun filteredHello(names: Array<String>): List<String> =
        names filter { it.length() > 3 } map { "Hello, ${it.toUpperCase()}!" }

/**
 * To generate n string with given generator.
 */
fun generate(n: Int, generator: () -> String): List<String> = (1..n).map { generator() }

/**
 * run an action and track the execution time.
 */
fun timer(action: () -> Unit): Long {

    val start = System.nanoTime()
    action()
    val end = System.nanoTime()

    return end - start
}

