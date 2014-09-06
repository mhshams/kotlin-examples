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
        assertEquals(3, a.value)

        val b = Box("ok")
        assertEquals("ok", b.value)
    }

    test fun `generic sources or providers (out)`() {

        val a: Provider<String> = Provider("ok")
        assertEquals("ok", a.next())

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
        /**
         * copies the first array to the second one
         */
        fun copy(from: Array<out Any>, to: Array<in Any>) {
            for (i in from.indices) {
                to[i] = from[i]
            }
        }

        //given
        val from = array(1, 2, 3)
        val to = array<Any>("a", "b", 0)

        //when
        copy(from, to)

        //then
        for (i in from.indices) {
            assertEquals(from[i], to[i])
        }
    }

    test fun `generic functions`() {
        /**
         * generates a single list of given generic type
         */
        fun singletonList<T>(item: T): List<T> {
            return listOf(item)
        }

        val x = singletonList('c')
        val y = singletonList(7)

        assertTrue(x is List<Char>)
        assertTrue(y is List<Int>)

        assertEquals(1, x.size)
        assertEquals(1, y.size)

        assertEquals('c', x[0])
        assertEquals(7, y[0])


    }

    test fun `extension generics`() {
        /**
         * a generic extension function.
         */
        fun <T> T.basicToString(): String {
            return "My String Value: ${this.javaClass.getName()}"
        }

        assertEquals("My String Value: java.lang.Integer", 1.basicToString())
        assertEquals("My String Value: java.lang.String", "ok".basicToString())
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


