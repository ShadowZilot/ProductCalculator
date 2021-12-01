package com.human_developing_soft.productcalc.add_editing.products.ui

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class SummaryPriceTest {
    @Test
    fun basicTest() {
        val testing = SummaryPrice.Base(
            12f, 20f,
            "Nothing"
        )
        assertEquals(12f * 20f, testing.price())
    }

    @Test
    fun noteTest() {
        val testing = SummaryPrice.Base(
            35f, 5f,
            "Nothing/|/${35f*5f+5f}"
        )
        assertEquals(35f*5f+5f, testing.price())
    }

    @Test
    fun emptyTest() {
        val testing = SummaryPrice.Base(5f, 5f,
            "Nothing/|/")
        assertEquals(0f, testing.price())
    }

    @Test
    fun floatingTest() {
        val testing = SummaryPrice.Base(5f, 5f,
            "Nothing/|/0.345")
        assertEquals(0.345f, testing.price())
    }
}