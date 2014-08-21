package examples.helloworld

import org.junit.Test as test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @author me
 */

/**
 * The old famous main method and 'hello world'
 */
fun main(args: Array<String>) {
    println("Hello, world!")
}

class HelloWorldTest {

    test fun `the old famous main method`() {
        //just a dummy call.
        main(array(""))

        assertTrue(true, "it was just a simple call to the main method")
    }

    test fun `main method with one argument`() {

        /**
         * Let's take the first name in args and greet him/her.
         */
        fun main(args: Array<String>): String {
            if (args.size == 0) {
                throw IllegalArgumentException("At least one argument is required.")
            }
            return "Hello, ${args[0]}!"
        }

        //expect
        assertEquals("Hello, Me!", main(array("Me")))

        //expect
        assertEquals("Hello, Me!", main(array("Me", "You")))
    }

    test fun `main method with multiple arguments`() {

        /**
         * Let's greet all of them.
         */
        fun main(args: Array<String>): List<String> = args map { "Hello, $it!" }

        //when
        val list = main(array("Me", "You"))

        //then
        assertEquals(2, list.size)
        assertEquals("Hello, Me!", list.get(0))
        assertEquals("Hello, You!", list.get(1))
    }

    test fun `greeting in different languages`() {

        /**
         * Greeting in different languages.
         */
        fun main(lang: String = "EN"): String = when (lang) {
            "EN" -> "Hello!"
            "ES" -> "¡Hola!"
            "RU" -> "Привет!"
            else -> "Sorry, I can't greet you in $lang yet"
        }

        //expect
        assertEquals("Hello!", main())

        //expect
        assertEquals("Hello!", main("EN"))

        //expect
        assertEquals("¡Hola!", main("ES"))

        //expect
        assertEquals("Привет!", main("RU"))

        //expect
        assertEquals("Sorry, I can't greet you in ME yet", main("ME"))
    }

    test fun `object oriented way of greeting`() {

        /**
         * Object oriented greeting.
         */
        class Greeter(val name: String) {

            fun greet(): String = "Hello, $name!"
        }

        //expect
        assertEquals("Hello, Me!", Greeter("Me").greet())

        //expect
        assertEquals("Hello, You!", Greeter("You").greet())
    }
}





