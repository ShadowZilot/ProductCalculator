package com.human_developing_soft.productcalc.product_storage.data

import com.human_developing_soft.productcalc.product_storage.domain.DomainProduct

class DataToDomainProduct : ProductMapper<DomainProduct> {
    override fun map(
        id: Int,
        dayId: Int,
        productNameId: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): DomainProduct {
        return DomainProduct.Base(
            id,
            productNameId,
            name,
            weight,
            priceForWeight,
            placeRow,
            note
        )
    }
}