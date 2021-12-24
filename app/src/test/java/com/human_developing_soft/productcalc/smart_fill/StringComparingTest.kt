package com.human_developing_soft.productcalc.smart_fill

import org.junit.Assert.*
import org.junit.Test

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class StringComparingTest {
    @Test
    fun basicTest() {
        val result = StringComparing.Base(
            "hello"
        ).comparedSame("hello")
        assertEquals(1f, result)
    }

    @Test
    fun notPerfectSame() {
        val result = StringComparing.Base(
            "Яблоко"
        ).comparedSame("яблока")
        println(result)
        assertEquals(true, result > 0.6f)
    }

    @Test
    fun notSame() {
        val result = StringComparing.Base(
            "Яблоко"
        ).comparedSame("Хурьма")
        println(result)
        assertEquals(true, result == 0f)
    }
}