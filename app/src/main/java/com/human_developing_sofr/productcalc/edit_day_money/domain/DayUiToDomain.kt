package com.human_developing_sofr.productcalc.edit_day_money.domain

import com.human_developing_sofr.productcalc.product_storage.domain.DayDomain
import com.human_developing_sofr.productcalc.product_storage.ui.DayUi

class DayUiToDomain : DayUi.Mapper<DayDomain> {
    override fun map(id: Int, money: Int, moneyRest: Int, productSumma: Int): DayDomain {
        return DayDomain.Base(id, money)
    }
}
