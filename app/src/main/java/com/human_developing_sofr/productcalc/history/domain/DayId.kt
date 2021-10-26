package com.human_developing_sofr.productcalc.history.domain

import com.human_developing_sofr.productcalc.product_storage.domain.AllDayDomain
import com.human_developing_sofr.productcalc.product_storage.domain.DayDomain
import com.human_developing_sofr.productcalc.product_storage.domain.DomainProduct
import com.human_developing_sofr.productcalc.product_storage.domain.ExpenditureDomain

class AllDayId : AllDayDomain.Mapper<Int> {
    override fun map(
        day: DayDomain,
        products: List<DomainProduct>,
        expenditures: List<ExpenditureDomain>
    ): Int {
        return day.map(DayId())
    }

    private class DayId : DayDomain.Mapper<Int> {
        override fun map(id: Int?, money: Int): Int {
            return id!!
        }
    }
}