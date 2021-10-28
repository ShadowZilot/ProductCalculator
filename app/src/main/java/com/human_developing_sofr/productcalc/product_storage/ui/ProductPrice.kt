package com.human_developing_sofr.productcalc.product_storage.ui

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