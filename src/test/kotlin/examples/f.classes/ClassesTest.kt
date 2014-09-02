package examples.classes

import org.junit.Test as test
import java.awt.event.MouseEvent
import java.awt.Button
import kotlin.test.assertEquals
import java.awt.event.MouseAdapter

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

    test fun `check nested class`() {
        assertEquals(2, Outer.Nested().foo())
    }

    test fun `check inner class`() {
        assertEquals(1, Outer().Inner().foo())
    }

    test fun `anonymous class`() {
        //TODO:Question: why do we need class body ( {} )here?
        val x = object : BaseFoo(1) {}

        assertEquals(1, x.y)
    }

    test fun `singleton object declaration`() {
        SINGLETON_LISTENER.mouseClicked(MouseEvent(Button(), 1, 1.toLong(), 1, 2, 3, 1, true))
    }
}

enum class Direction () {
    NORTH; SOUTH; WEST; EAST
}

class Outer() {

    private val bar: Int = 1

    class Nested() {
        fun foo() = 2
    }

    public inner class Inner() {
        fun foo() = bar
    }
}

open class BaseFoo(x: Int) {
    public open val y: Int = x
}

object SINGLETON_LISTENER : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        assertEquals(1, e.getID())
        assertEquals(2, e.getX())
        assertEquals(3, e.getY())
    }
}

open class MyFoo(val s: String) {
    open fun foo() {
        println("hello")
    }
}

class MyBar(i: Int) : MyFoo(i.toString()) {
    override fun foo() {
        super.foo()
        println("and bye")
    }
}

open class A {
    open fun f() {
        print("A")
    }
    fun a() {
        print("a")
    }
}

trait B {
    fun f() {
        print("B")
    }
    fun b() {
        print("b")
    }
}

class C() : A(), B {
    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}

class D : B {
    override fun f() {
        print("B")
    }
    override fun b() {
        print("b")
    }
}

open class Base {
    open val p: Int
        get() = 1
}

class Derived : Base() {
    override val p: Int
        get() = 2
}

class CC private () {
    class object {
        fun create() = C()
    }
}

fun mainCC() {
    val c = CC.create() // C denotes the class object here
}

abstract class Factory<out T> {
    abstract fun create(): T
}

open class CCC() {
    class object : Factory<CCC>() {
        override fun create(): CCC = CCC()
    }
}

fun mainCCC() {
    val c = CCC.create()
}



enum class Color public (val rgb: Int) {
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

    abstract fun signal(): ProtocolState
}




