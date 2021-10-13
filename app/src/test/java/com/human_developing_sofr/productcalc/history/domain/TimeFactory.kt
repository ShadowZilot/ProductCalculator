package com.human_developing_sofr.productcalc.history.domain

import java.util.*

class TimeFactory(
    private val mYear: Int,
    private val mMonth: Int,
    private val mDay: Int
) {
    fun createTime(): Long {
        return GregorianCalendar(
            mYear, mMonth, mDay
        ).timeInMillis
    }
}