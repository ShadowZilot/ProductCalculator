package com.human_developing_sofr.productcalc.product_storage.data

import com.human_developing_sofr.productcalc.product_storage.domain.DayDomain

class DayDomainToData(
    private val mTime: Long
) : DayDomain.Mapper<Day> {
    override fun map(id: Int?, money: Int): Day {
        return Day(id, money, mTime)
    }
}