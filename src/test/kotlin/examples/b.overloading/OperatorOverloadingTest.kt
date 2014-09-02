package examples.operator

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.util.ArrayList
import java.util.HashMap
import kotlin.test.assertFalse

/**
 * @author me
 */
class OperatorOverloadingTest {

    test fun `plus operator`() {

        //given
        val foo = Foo("Foo")
        val bar = Foo("Bar")

        //when
        val fooBar = foo.plus(bar)
        //then
        assertEquals("FooBar", fooBar.value)

        //when
        val barFoo = bar + foo
        //then
        assertEquals("BarFoo", barFoo.value)
    }

    test fun `index operator`() {
        val l = listOf(1, 2, 3, 4, 5)

        for (i in 0..l.lastIndex) {
            assertEquals(l[i], l.get(i))
        }
    }

    test fun `index operator in Foo`() {
        //given
        val foo = Foo("Foo")

        //expect
        assertEquals('F', foo[0])
        assertEquals('o', foo[1])
        assertEquals('o', foo[2])
    }

    test fun `comparison operator`() {
        //given
        val foo = Foo("Foo")
        val bar = Foo("Bar")

        //expect
        assertTrue(foo.compareTo(bar) > 0)
        //expect
        assertTrue(foo > bar)
        assertTrue(bar < foo)
        assertFalse(bar == foo)
    }

    test fun `substring operator`() {
        //given
        val foo = Foo("HELLO")

        //expect
        assertEquals("ELL", foo.get(1, 4))
        //expect
        assertEquals("LO", foo[3, 5])
    }

    test fun `invoke operator`() {
        //given
        val foo = Foo("HELLO")

        //expect
        assertEquals(5, foo.invoke(2, 3))
        //expect
        assertEquals(7, foo(4, 3))
    }

    test fun `put and get in maps`() {
        //given
        val map: MutableMap<String, String> = HashMap()

        //when
        map.put("key 1", "yes")
        //then
        assertEquals("yes", map["key 1"])

        //when
        map["key 2"] = "no"
        //then
        assertEquals("no", map["key 2"])
    }
}

/**
 * Let's try operator over loading.
 */
class Foo(val value: String) : Comparable<Foo> {

    /**
     * by convention '+' operator
     */
    fun plus(other: Foo): Foo = Foo(this.value.concat(other.value))

    /**
     * by convention '<', '==' and '>' operators
     */
    public override fun compareTo(other: Foo): Int = this.value.compareTo(other.value)

    /**
     * by conversion '[i]'
     */
    fun get(i: Int): Char = this.value[i]

    /**
     * by convention '[i, j]'
     */
    fun get(i: Int, j: Int): String = this.value.substring(i, j)

    /**
     * by convention 'object(i, j)'
     */
    fun invoke(i: Int, j: Int): Int = i + j
}
