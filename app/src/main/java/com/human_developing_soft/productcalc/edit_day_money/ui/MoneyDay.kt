package com.human_developing_soft.productcalc.edit_day_money.ui

import com.human_developing_soft.productcalc.product_storage.domain.DayDomain

class MoneyDay : DayDomain.Mapper<Int> {
    override fun map(id: Int?, money: Int) = money
}