package com.human_developing_sofr.productcalc.product_storage.data

import androidx.room.Embedded
import androidx.room.Relation

data class AllDay(
    @Embedded
    private val mDay: Day,
    @Relation(
        parentColumn = "mId",
        entityColumn = "dayId"
    )
    private val products: List<Product>
) {

    fun getDay() = mDay

    fun getProducts() = products

    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(
            mDay,
            products
        )
    }

    interface Mapper<T> {
        fun map(
            day: Day,
            products: List<Product>
        ): T
    }
}

