package com.human_developing_soft.productcalc.product_storage.ui

import com.human_developing_soft.productcalc.product_storage.domain.ExpenditureDomain

class ExpenditurePrice : ExpenditureUi.Mapper<Int> {
    override fun map(id: Int?, name: String, cost: Float, note: String): Int {
        return cost.toInt()
    }
}

class ExpenditureDomainPrice : ExpenditureDomain.Mapper<Int> {
    override fun map(id: Int?, name: String, price: Float, note: String): Int {
        return price.toInt()
    }
}