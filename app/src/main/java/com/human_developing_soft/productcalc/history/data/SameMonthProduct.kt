package com.human_developing_soft.productcalc.history.data

import com.human_developing_soft.productcalc.history.domain.DayTiming
import com.human_developing_soft.productcalc.history.domain.SeparatedDay
import java.util.*

class SameMonthProduct(
    private val mTiming: SeparatedDay
) : SeparatedDay.Mapper<Boolean> {
    override fun map(commonTime: Long): Boolean {
        val selfDate = GregorianCalendar.getInstance()
        selfDate.time = Date(mTiming.map(DayTiming()))
        val thisDate = GregorianCalendar.getInstance()
        thisDate.time = Date(commonTime)
        return (selfDate.get(Calendar.MONTH) == thisDate.get(Calendar.MONTH)
                && selfDate.get(Calendar.YEAR) == thisDate.get(Calendar.YEAR))
    }
}