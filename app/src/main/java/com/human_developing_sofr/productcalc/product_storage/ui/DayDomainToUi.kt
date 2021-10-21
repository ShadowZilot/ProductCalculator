package com.human_developing_sofr.productcalc.product_storage.ui

import com.human_developing_sofr.productcalc.product_storage.domain.DayDomain

class DayDomainToUi(
    private val mProductSumma: Int
) : DayDomain.Mapper<DayUi> {
    override fun map(id: Int?, money: Int): DayUi {
        return DayUi.Base(
            id!!,
            money,
            mProductSumma
        )
    }
}