package com.human_developing_sofr.productcalc.ae_products.domain

import com.human_developing_sofr.productcalc.product_storage.domain.DomainProduct
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

class UiToDomainProduct : ProductUi.Mapper<DomainProduct> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: Int,
        note: String
    ): DomainProduct {
        return DomainProduct.Base(
            id,
            name,
            weight,
            priceForWeight,
            placeRow,
            note
        )
    }
}