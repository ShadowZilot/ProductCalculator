package com.human_developing_soft.productcalc.product_storage.data

interface ProductMapper<T> {
    fun map(
        id: Int,
        dayId: Int,
        productNameId: Int?,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: String,
        note: String
    ): T
}