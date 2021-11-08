package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

class ProductPrice : ProductUi.Mapper<Int> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: String,
        note: String
    ): Int {
        return priceSummary.toInt()
    }
}