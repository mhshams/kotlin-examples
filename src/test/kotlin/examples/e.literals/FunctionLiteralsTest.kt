package examples.functionliterals

import org.junit.Test as test
import kotlin.test.assertEquals

/**
 *
 * @author me
 *
 */
class FunctionLiteralsTest {
    test fun `function literal`() {
        // passing a function literal to another function
        val m = max(listOf(4, 20, 100, 4, 2), { i, j -> i < j })

        assertEquals(100, m)
    }

    test fun `another function literal`() {
        val less: (Int, Int) -> Boolean = {(x, y) -> x < y }
        val m = max(listOf(1, 4, 20, 100, 2), less)

        assertEquals(100, m)
    }

    test fun `sum as a normal function`() {
        /**
         * Calculates the sum of all elements in the given collection
         */
        fun sum(numbers: Collection<Int>): Int {
            return numbers.filter { it > 0 }.sum()
        }

        assertEquals(15, sum(listOf(1, 2, 0, -1, 4, 8, -4, -5, 0)))
    }

    test fun `sum as an extension function`() {
        val sum = { Int.(other: Int): Int -> this + other }

        assertEquals(3, 0.sum(3))
        assertEquals(5, 2.sum(3))
    }
}

/**
 * A generic function that founds the biggest element in the list, according to comparison function that is passed.
 */
fun max<T>(list: List<T>, less: (T, T) -> Boolean): T {
    var max: T = list[0]

    for (it in list)
        if (less(max, it))
            max = it

    return max
}

