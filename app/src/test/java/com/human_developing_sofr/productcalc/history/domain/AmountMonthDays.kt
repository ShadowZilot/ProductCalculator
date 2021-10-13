package com.human_developing_sofr.productcalc.history.domain

class AmountMonthDays(
    private val mExpectedAmount: Int
) : MonthDomain.Mapper<Boolean> {
    override fun map(time: Long, days: List<Long>): Boolean {
        return mExpectedAmount == days.size
    }
}