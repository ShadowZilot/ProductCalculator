package com.human_developing_sofr.productcalc.history.data

import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.data.ProductMapper
import java.util.*

class SameMonthProduct(
    private val mOtherProduct: Product
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
        otherDate.time = Date(mOtherProduct.getTime())
        val selfDate = GregorianCalendar.getInstance()
        selfDate.time = Date(time)
        return otherDate.get(Calendar.MONTH) == selfDate.get(Calendar.MONTH)
    }
}