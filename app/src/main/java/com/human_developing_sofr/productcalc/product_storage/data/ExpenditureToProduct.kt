package com.human_developing_sofr.productcalc.product_storage.data

import com.human_developing_sofr.productcalc.product_storage.domain.ExpenditureDomain

class ExpenditureToProduct(
    private val mDayId: Int
) : ExpenditureDomain.Mapper<Product> {
    override fun map(id: Int?, name: String, price: Float, note: String): Product {
        return Product(
            id,
            mDayId,
            name,
            1f,
            price,
            "",
            "${ExpenditureString.value()}$note"
        )
    }
}