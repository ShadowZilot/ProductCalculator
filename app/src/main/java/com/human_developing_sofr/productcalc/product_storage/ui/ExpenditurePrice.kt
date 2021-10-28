package com.human_developing_sofr.productcalc.product_storage.ui

class ExpenditurePrice : ExpenditureUi.Mapper<Int> {
    override fun map(id: Int, name: String, cost: Float, note: String): Int {
        return cost.toInt()
    }
}