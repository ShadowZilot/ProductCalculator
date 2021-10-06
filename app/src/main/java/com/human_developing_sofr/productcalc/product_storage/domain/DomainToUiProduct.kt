package com.human_developing_sofr.productcalc.product_storage.domain

import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

class DomainToUiProduct : DomainProduct.Mapper<ProductUi> {
    override fun map(
        id: Int,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: Int,
        note: String
    ): ProductUi {
        return ProductUi.Base(
            id,
            name,
            weight,
            priceForWeight,
            weight * priceForWeight,
            placeRow,
            note
        )
    }
}