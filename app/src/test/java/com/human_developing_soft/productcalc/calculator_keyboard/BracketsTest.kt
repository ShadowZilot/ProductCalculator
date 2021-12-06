package com.human_developing_soft.productcalc.calculator_keyboard

import org.junit.Assert.*
import org.junit.Test

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class BracketsTest {
    @Test
    fun basicTest() {
        val brackets = Brackets(0)
        assertEquals(
            "(",
            brackets.implementAction("")
        )
    }

    @Test
    fun afterNumberTest() {
        val brackets = Brackets(2)
        assertEquals(
            "56*(",
            brackets.implementAction("56")
        )
    }

    @Test
    fun afterMathTest() {
        val brackets = Brackets(6)
        assertEquals(
            "56.25-(",
            brackets.implementAction("56.25-")
        )
    }

    @Test
    fun complexTest() {
        val brackets = Brackets(9)
        assertEquals(
            "7-(67+78)*(",
            brackets.implementAction("7-(67+78)")
        )
    }
}