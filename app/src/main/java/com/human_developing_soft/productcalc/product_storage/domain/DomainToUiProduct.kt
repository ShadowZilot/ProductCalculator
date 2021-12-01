package com.human_developing_soft.productcalc.product_storage.domain

import com.human_developing_soft.productcalc.add_editing.products.ui.SummaryPrice
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

class DomainToUiProduct : DomainProduct.Mapper<ProductUi> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): ProductUi {
        return ProductUi.Base(
            id,
            name,
            weight,
            priceForWeight,
            SummaryPrice.Base(
                priceForWeight, weight, note
            ).price(),
            placeRow,
            note
        )
    }
}