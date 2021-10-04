package com.human_developing_sofr.productcalc.product_storage.data

interface ProductMapper<T> {
    fun map(
        id: Int,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: Int,
        note: String,
        time: Long
    ): T
}