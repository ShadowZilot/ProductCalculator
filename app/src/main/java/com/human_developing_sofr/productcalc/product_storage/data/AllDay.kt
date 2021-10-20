package com.human_developing_sofr.productcalc.product_storage.data

import androidx.room.Embedded
import androidx.room.Relation

data class AllDay(
    @Embedded
    private val mDay: Day,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayId"
    )
    private val mProducts: List<Product>
) {
    fun <T> map(mapper: Mapper<T>): T {
        return mapper.map(
            mDay,
            mProducts
        )
    }

    interface Mapper<T> {
        fun map(
            day: Day,
            products: List<Product>
        ): T
    }
}

