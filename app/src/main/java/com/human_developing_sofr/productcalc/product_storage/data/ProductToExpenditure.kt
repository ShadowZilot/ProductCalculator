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
        val result : ExpenditureDomain
        if (note.contains(ExpenditureString.value())) {
            result = ExpenditureDomain.Base(
                id, name, priceForWeight, note.removePrefix(
                    ExpenditureString.value()
                )
            )
        } else {
            throw NotExpenditureException("Entity with id = $id is not expenditure")
        }
        return result
    }
}