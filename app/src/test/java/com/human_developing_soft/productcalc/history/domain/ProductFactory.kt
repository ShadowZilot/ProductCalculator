package com.human_developing_soft.productcalc.history.domain

import com.human_developing_soft.productcalc.product_storage.data.Product
import java.util.*

/**
 * This class creates Products
 * objects for test code separating days
 */
class ProductFactory(
    private val mYear: Int,
    private val mMonth: Int,
    private val mDay: Int
) {
    fun createProduct(): Product {
        return Product(
            0,
            "a", 1f, 1f,
            1, "b",
            GregorianCalendar(mYear, mMonth, mDay).timeInMillis
        )
    }
}