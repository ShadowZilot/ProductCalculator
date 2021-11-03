package com.human_developing_soft.productcalc.history.domain

import org.junit.Test

class MonthSeparatingListTest {
    @Test
    fun simpleMonthSeparating() {
        val result = MonthSeparatingList.Base(
            FakeDaySeparatingList(
                TimeFactory(2021,
                    10, 4
                ).createTime(),
                TimeFactory(2021,
                10, 5
                ).createTime(),
                TimeFactory(2021,
                    11, 4
                ).createTime()
            )
        ).separate()
        assert(result.size == 2 && result[0].map(AmountMonthDays(2))
                && result[1].map(AmountMonthDays(1)))
    }

    @Test
    fun singleMonth() {
        val result = MonthSeparatingList.Base(
            FakeDaySeparatingList(
                TimeFactory(2021,
                    10, 4
                ).createTime(),
                TimeFactory(2021,
                    10, 5
                ).createTime()
            )
        ).separate()
        assert(result.size == 1 && result[0].map(AmountMonthDays(2)))
    }

    @Test
    fun threeMonth() {
        val result = MonthSeparatingList.Base(
            FakeDaySeparatingList(
                TimeFactory(2021,
                10, 4
                ).createTime(),
                TimeFactory(2021,
                    10, 5
                ).createTime(),
                TimeFactory(2021,
                    11, 4
                ).createTime(),
                TimeFactory(2021,
                    11, 5
                ).createTime(),
                TimeFactory(2021,
                    12, 4
                ).createTime(),
                TimeFactory(2021,
                    12, 5
                ).createTime()
            )
        ).separate()
        assert(result.size == 3 && result[0].map(AmountMonthDays(2))
                && result[1].map(AmountMonthDays(2))
                && result[2].map(AmountMonthDays(2)))
    }

    @Test
    fun singleMonthSingleDay() {
        val result = MonthSeparatingList.Base(
            FakeDaySeparatingList(
                TimeFactory(2021,
                    10, 5
                ).createTime()
            )
        ).separate()
        assert(result.size == 1 && result[0].map(AmountMonthDays(1)))
    }
}