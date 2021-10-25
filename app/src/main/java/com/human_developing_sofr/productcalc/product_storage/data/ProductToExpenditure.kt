package com.human_developing_sofr.productcalc.product_storage.data

import com.human_developing_sofr.productcalc.product_storage.domain.ExpenditureDomain

class ProductToExpenditure : ProductMapper<ExpenditureDomain> {
    override fun map(
        id: Int,
        dayId: Int,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): ExpenditureDomain {
        return ExpenditureDomain.Base(
            id, name, priceForWeight, note.removePrefix(
                ExpenditureString.value()
            )
        )
    }
}