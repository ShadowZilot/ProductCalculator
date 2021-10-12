package com.human_developing_sofr.productcalc.history.domain

import com.human_developing_sofr.productcalc.history.data.SameMonthProduct

interface MonthSeparatingList {
    fun separate(): List<MonthDomain>

    class Base(
        private val mSeparatedDay: DaySeparatingList
    ) : MonthSeparatingList {
        override fun separate(): List<MonthDomain> {
            val days = mSeparatedDay.separate()
            val result = mutableListOf<MonthDomain>()
            val tmpDays = mutableListOf<SeparatedDay>()
            if (days.size == 1) tmpDays.add(days[0])
            for (i in 0 until days.size-1) {
                val comparator = SameMonthProduct(days[i])
                tmpDays.add(days[i])
                if (!days[i+1].map(comparator)) {
                    result.add(MonthDomain.Base(
                        tmpDays.map {
                            it.map(DayTiming())
                        }
                    ))
                    tmpDays.clear()
                }
            }
            if (tmpDays.isNotEmpty()) {
                result.add(MonthDomain.Base(
                    tmpDays.map {
                        it.map(DayTiming())
                    }
                ))
            }
            return result
        }
    }
}