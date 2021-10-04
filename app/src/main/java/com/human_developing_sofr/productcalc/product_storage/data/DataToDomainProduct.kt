package com.human_developing_sofr.productcalc.product_storage.data

import com.human_developing_sofr.productcalc.product_storage.domain.DomainProduct

class DataToDomainProduct : ProductMapper<DomainProduct> {
    override fun map(
        id: Int,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: Int,
        note: String,
        time: Long
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