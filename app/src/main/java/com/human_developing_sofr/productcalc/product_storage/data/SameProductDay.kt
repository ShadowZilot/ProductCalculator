package com.human_developing_sofr.productcalc.product_storage.data

class SameProductDay(
    private val mCurrentDate: Long
) : ProductMapper<Boolean> {
    override fun map(
        id: Int,
        name: String,
        weight: Float,
        priceForWeight: Float,
        placeRow: Int,
        note: String,
        time: Long
    ): Boolean {
        val currentAmountDays = (mCurrentDate / 1000) / 86400
        val productAmountDays = (time / 1000) / 86400
        return currentAmountDays == productAmountDays
    }
}