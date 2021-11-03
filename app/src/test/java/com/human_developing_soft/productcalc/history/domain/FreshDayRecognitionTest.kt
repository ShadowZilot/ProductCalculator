package com.human_developing_soft.productcalc.history.domain

import org.junit.Test

class FreshDayRecognitionTest {
    @Test
    fun basicTest() {
        val testValue = FreshDayRecognition.Base(
            TimeFactory(2021,
                10, 5
            ).createTime(),
            TimeFactory(2021,
                10, 10
            ).createTime()
        ).isFresh()
        assert(testValue)
    }

    @Test
    fun unFreshDay() {
        val testValue = FreshDayRecognition.Base(
            TimeFactory(2021,
            10, 10
            ).createTime(),
            TimeFactory(2021,
                10, 25
            ).createTime()
        ).isFresh()
        assert(!testValue)
    }

    @Test
     fun differentMonth() {
         val testValue = FreshDayRecognition.Base(
             TimeFactory(2021,
                 9, 25
             ).createTime(),
             TimeFactory(2021,
                 10, 1
             ).createTime()
         ).isFresh()
        assert(testValue)
     }
}