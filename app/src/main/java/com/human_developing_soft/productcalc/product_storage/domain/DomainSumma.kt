package com.human_developing_soft.productcalc.product_storage.domain

import com.human_developing_soft.productcalc.add_editing.products.ui.SummaryPrice

class DomainSumma : DomainProduct.Mapper<Float> {
    override fun map(
        id: Int?,
        productNameId: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): Float {
        return SummaryPrice.Base(priceForWeight,
            weight, note).price()
    }
}