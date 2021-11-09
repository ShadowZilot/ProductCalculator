package com.human_developing_soft.productcalc.history.domain

import com.human_developing_soft.productcalc.product_storage.data.AllDay
import com.human_developing_soft.productcalc.product_storage.data.Day
import java.util.*

class AllDayFactory(
    private val mYear: Int,
    private val mMonth: Int,
    private val mDay: Int
) {
    fun createTime(): AllDay {
        return AllDay(
            Day(
                1,
                0,
                GregorianCalendar(
                    mYear, mMonth, mDay
                ).timeInMillis
            ),
            emptyList()
        )
    }
}