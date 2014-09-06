package examples.classes

import org.junit.Test as test
import java.awt.event.MouseEvent
import java.awt.Button
import kotlin.test.assertEquals
import java.awt.event.MouseAdapter
import kotlin.test.assertTrue

/**
 *
 * @author me
 *
 */

class ClassesTest {

    test fun `check enums`() {
        val values = Direction.values()

        assertEquals(4, values.size)
        assertEquals(Direction.NORTH, values[0])
        assertEquals(Direction.SOUTH, values[1])
        assertEquals(Direction.WEST, values[2])
        assertEquals(Direction.EAST, values[3])
    }

    test fun `check color enums`() {
        assertEquals(0xFF0000, Color.RED.rgb)
        assertEquals(0x00FF00, Color.GREEN.rgb)
        assertEquals(0x0000FF, Color.BLUE.rgb)
    }

    test fun `check enums with abstract functions`() {
        assertEquals(ProtocolState.TALKING, ProtocolState.WAITING.signal())
        assertEquals(ProtocolState.WAITING, ProtocolState.TALKING.signal())
    }

    test fun `check nested class`() {
        // no need have an instance or Outer
        assertEquals(2, Outer.Nested().foo())
    }

    test fun `check inner class`() {
        //an instance of Outer is needed
        val outer = Outer()
        assertEquals(outer.bar, outer.Inner().foo())
    }

    test fun `anonymous class`() {
        //TODO:Question: why do we need class body ({}) here?
        val x = object : BaseFoo(1) {}

        assertEquals(1, x.y)
    }

    test fun `singleton object declaration`() {
        SINGLETON_LISTENER.mouseClicked(MouseEvent(Button(), 1, 1.toLong(), 1, 2, 3, 1, true))
    }

    test fun `override functions`() {
        assertEquals("hello foo from parent", MyFoo("foo").foo())
        assertEquals("hello bar from parent and child", MyBar("bar").foo())
    }

    test fun `class object and factory methods`() {

        //since the constructor is private, factory method is the only way of instantiation
        assertTrue(ClassObject.create() is ClassObject)

        //same for class object factory
        assertTrue(ClassObjectFactory.create() is ClassObjectFactory)
    }

    test fun `property overriding`() {

        assertEquals(1, Base().foo)
        //overrides property
        assertEquals(2, Derived().foo)
    }

    test fun `check traits`() {
        //default implementations
        assertEquals("Hello!", Bar().sayHi())
        assertEquals("Bye!", Bar().sayBye())

        //default implementation
        assertEquals("Hello!", Baz().sayHi())
        //a custom implementation in Baz
        assertEquals("Good Bye!", Baz().sayBye())

        assertEquals("Hi!", Conflict().sayHi())
    }
}

/**
 * A simple enum class
 */
enum class Direction() {
    NORTH; SOUTH; WEST; EAST
}

/**
 * enum class with constructor
 */
enum class Color(val rgb: Int) {
    RED : Color(0xFF0000)
    GREEN : Color(0x00FF00)
    BLUE : Color(0x0000FF)
}

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    }

    TALKING {
        override fun signal() = WAITING
    }

    /**
     * an abstract method to be implemented
     */
    abstract fun signal(): ProtocolState
}

/**
 * A class with:
 *  - a nested class
 *  - and an inner class
 */
class Outer() {

    val bar: Int = 1

    /**
     * doesn't have access to parent class properties (e.g: bar)
     * It is more like 'static inner' classes in java
     */
    class Nested() {
        fun foo() = 2
    }

    /**
     * It does have access to parent class properties
     * It is more like a non-static inner class in java
     */
    public inner class Inner() {
        fun foo() = bar
    }
}

/**
 * Classes by default are closed (final or not inheritable). we use 'open' to let them be inheritable.
 */
open class BaseFoo(x: Int) {
    public open val y: Int = x
}

/**
 * A singleton object.
 * Note that class and (singleton) object definition are mixed together.
 */
object SINGLETON_LISTENER : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        assertEquals(1, e.getID())
        assertEquals(2, e.getX())
        assertEquals(3, e.getY())
    }
}

/**
 * an open class
 */
open class MyFoo(val name: String) {
    /**
     * only open functions could be overridden.
     */
    open fun foo(): String {
        return "hello $name from parent"
    }
}

/**
 * a child class
 */
class MyBar(name: String) : MyFoo(name) {
    override fun foo(): String = "${super.foo()} and child"
}

/**
 * class object
 */
class ClassObject private() {
    class object {
        // a factory method - (static factory methods in java)
        fun create() = ClassObject()
    }
}


abstract class Factory<out T> {
    abstract fun create(): T
}

/**
 * class object with inheritance
 */
open class ClassObjectFactory private() {
    class object : Factory<ClassObjectFactory>() {
        override fun create() = ClassObjectFactory()
    }
}

/**
 * property overriding
 */
open class Base {
    open val foo: Int
        get() = 1
}

class Derived : Base() {
    override val foo: Int
        get() = 2
}

/**
 * A trait
 */
trait Foo {
    //default implementation
    fun sayHi(): String = "Hello!"
    //default implementation
    fun sayBye(): String = "Bye!"
}

//bar is happy with default implementations
class Bar : Foo

class Baz : Foo {
    //overrides the default behaviour
    override fun sayBye() = "Good Bye!"
}

open class Qux {
    open fun sayHi(): String = "Hi!"
}

class Conflict() : Qux(), Foo {
    //we have to override the method here. because of method signature conflict between Qux and Foo
    override fun sayHi(): String = super<Qux>.sayHi()
}




