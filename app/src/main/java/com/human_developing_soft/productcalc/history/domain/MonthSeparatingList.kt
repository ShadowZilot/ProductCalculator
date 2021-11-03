package com.human_developing_soft.productcalc.history.domain

interface MonthSeparatingList {
    fun separate(): List<MonthDomain>

    /*class Base(
        private val mSeparatedDay: DaySeparatingList
    ) : MonthSeparatingList {
        override fun separate(): List<MonthDomain> {
            val result = mutableListOf<MonthDomain>()
            val days = mSeparatedDay.separate()
            var last = 0
            for (i in days.indices) {
                val comparator = SameMonthProduct(days[i])
                try {
                    if (!days[i + 1].map(comparator)) {
                        addDay(result, days, last, i)
                        last = i
                    }
                } catch (e: IndexOutOfBoundsException) {
                    addDay(result, days, last, i)
                }
            }
            return result
        }

        private fun addDay(
            result: MutableList<MonthDomain>,
            days: List<SeparatedDay>,
            last: Int,
            i: Int
        ) {
            result.add(
                MonthDomain.Base(
                    if (result.isNotEmpty())
                        days.subList(last, i).map {
                            it.map(DayTiming())
                        }
                    else days.slice(last..i).map {
                        it.map(DayTiming())
                    }
                )
            )
        }
    }*/
}