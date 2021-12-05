package com.human_developing_soft.productcalc.calculator_keyboard

import org.junit.Assert.*
import org.junit.Test

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class NumberTest {
    @Test
    fun basicTest() {
        val num = Number("5", 0)
        assertEquals("5", num.implementAction(""))
    }
}