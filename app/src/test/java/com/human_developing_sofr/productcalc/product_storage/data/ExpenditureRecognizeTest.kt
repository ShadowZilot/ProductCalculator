package com.human_developing_sofr.productcalc.product_storage.data

import org.junit.Test

class ExpenditureRecognizeTest {
    @Test
    fun simple() {
        val testValue : Boolean = Product(
            1, "a", 1f,
            1f, 1, "[*$*]", 0
        ).map(ExpenditureRecognize())
        assert(testValue)
    }

    @Test
    fun simpleWrong() {
        val testValue = Product(
            1, "a", 1f,
            1f, 1, "", 0
        ).map(ExpenditureRecognize())
        assert(!testValue)
    }

    @Test
    fun withOtherText() {
        val testValue = Product(
            1, "a", 1f,
            1f, 1, "2 мешка[*$*]",
            0
        ).map(ExpenditureRecognize())
        assert(testValue)
    }

    @Test
    fun withOtherTextWrong() {
        val testValue = Product(
            1, "a", 1f,
            1f, 1, "2 мешка",
            0
        ).map(ExpenditureRecognize())
        assert(!testValue)
    }
}