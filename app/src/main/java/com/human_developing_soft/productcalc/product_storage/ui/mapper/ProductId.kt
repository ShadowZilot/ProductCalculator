package com.human_developing_soft.productcalc.product_storage.ui.mapper

import com.human_developing_soft.productcalc.add_editing.products.domain.WrongProductException
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

class ProductId : ProductUi.Mapper<Int> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: String,
        note: String
    ) = id ?: throw WrongProductException("Handle product with empty ID")
}