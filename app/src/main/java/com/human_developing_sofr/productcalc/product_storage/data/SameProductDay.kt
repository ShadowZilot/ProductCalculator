package com.human_developing_sofr.productcalc.product_storage.data

import java.util.*

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
        val otherDate = GregorianCalendar.getInstance()
        otherDate.time = Date(mCurrentDate)
        val selfDate = GregorianCalendar.getInstance()
        selfDate.time = Date(time)
        return (otherDate.get(Calendar.DAY_OF_YEAR) == selfDate.get(Calendar.DAY_OF_YEAR)
                && otherDate.get(Calendar.YEAR) == selfDate.get(Calendar.YEAR))
    }
}