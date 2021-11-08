package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.domain.DayDomain
import com.human_developing_soft.productcalc.product_storage.ui.DayUi

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