package com.human_developing_sofr.productcalc.edit_day_money.ui

import com.human_developing_sofr.productcalc.product_storage.domain.DayDomain

class MoneyDay : DayDomain.Mapper<Int> {
    override fun map(id: Int?, money: Int) = money
}