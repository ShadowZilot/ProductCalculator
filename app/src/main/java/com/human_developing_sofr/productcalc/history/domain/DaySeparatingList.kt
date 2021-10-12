package com.human_developing_sofr.productcalc.history.domain

import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.data.SameProductDay

interface DaySeparatingList {
    fun separate(): List<SeparatedDay>

    class Base(
        private val mProducts : List<Product>
    ): DaySeparatingList {
        override fun separate(): List<SeparatedDay> {
            val result = mutableListOf<SeparatedDay>()
            result.add(SeparatedDay.Base(mProducts[0].getTime()))
            for (i in 0 until mProducts.size-1) {
                val comparator = SameProductDay(mProducts[i].getTime())
                if (!mProducts[i+1].map(comparator)) {
                    result.add(SeparatedDay.Base(mProducts[i+1].getTime()))
                }
            }
            return result
        }
    }
}