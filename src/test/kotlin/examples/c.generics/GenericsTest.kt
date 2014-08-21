package examples.generics

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 *
 * @author me
 *
 */
class GenericsTest {
    test fun `a simple box class`() {
        val a: Box<Int> = Box(3)
        val b = Box(9)

        assertEquals(3, a.value)
        assertEquals(9, b.value)
    }

    test fun `generic sources or providers (out)`() {
        val a = Provider("ok")
        val b: Provider<Any> = a

        assertEquals("ok", b.next())
    }

    test fun `generic consumers (in)`() {
        val n = NumberConsumer()

        //consume double
        val d: Double = 1.0
        assertEquals(1, n.consume(d))

        //consume long
        val l: Long = 1
        assertEquals(1, n.consume(l))
    }

    test fun `a generic copy function`() {
        val from = array(1, 2, 3)
        val to = array<Any>("", "", "")

        copy(from, to)

        assertEquals(from[0], to[0])
        assertEquals(from[1], to[1])
        assertEquals(from[2], to[2])
    }

    test fun `generic functions`() {
        val x = singletonList('c')

        assertTrue(x is List<Char>)
        assertEquals(1, x.size)
        assertEquals('c', x[0])
    }

    test fun `extension generics`() {
        assertEquals("java.lang.Integer", 1.basicToString())
        assertEquals("java.lang.String", "ok".basicToString())
    }
}

/**
 * box T - boxes given generic type
 */
class Box<T>(val value: T)

/**
 * T provider - provides given generic type
 */
class Provider<out T>(val v: T) {
    fun next(): T = v
}

/**
 * T consumer - consumes given generic type
 */
abstract class Consumer<in T> {
    abstract fun consume(other: T): Int
}

/**
 * a number consumer - consumes only numbers (all type of numbers)
 */
class NumberConsumer : Consumer<Number>() {
    override fun consume(other: Number) = other.toInt()
}

/**
 * copies the first array to the second one
 */
fun copy(from : Array<out Any>, to : Array<in Any>) {
    for (i in from.indices)
        to[i] = from[i]
}

/**
 * generates a single list of given generic type
 */
fun singletonList<T>(item : T) : List<T> {
    return listOf(item)
}

/**
 * a generic extension function.
 */
fun <T> T.basicToString() : String {
    return this.javaClass.getName()
}


