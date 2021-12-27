package com.human_developing_soft.productcalc.add_editing.products.domain

import com.human_developing_soft.productcalc.product_storage.domain.DomainProduct
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

class UiToDomainProduct : ProductUi.Mapper<DomainProduct> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        priceSummary: Float,
        placeRow: String,
        note: String
    ): DomainProduct {
        return DomainProduct.Base(
            id,
            null,
            name,
            weight,
            priceForWeight,
            placeRow,
            note + "/|/${priceSummary}"
        )
    }
}