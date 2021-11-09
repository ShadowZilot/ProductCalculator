package com.human_developing_soft.productcalc.history.domain

import java.util.*

class TimeFactory(
    year: Int,
    month: Int,
    day: Int
)  {
    private val mCalendar = GregorianCalendar()

    init {
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, month)
        mCalendar.set(Calendar.DAY_OF_MONTH, day)
    }

    fun createTime() = mCalendar.timeInMillis
}