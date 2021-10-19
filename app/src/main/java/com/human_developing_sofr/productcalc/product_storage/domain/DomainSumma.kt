package com.human_developing_sofr.productcalc.product_storage.domain

class DomainSumma : DomainProduct.Mapper<Float> {
    override fun map(
        id: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): Float {
        return priceForWeight * weight
    }
}