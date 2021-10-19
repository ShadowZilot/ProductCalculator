package com.human_developing_sofr.productcalc.product_storage.data

class ExpenditureRecognize : ProductMapper<Boolean> {
    override fun map(
        id: Int,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: Int,
        note: String,
        time: Long
    ): Boolean {
        return note.contains("[*$*]")
    }
}