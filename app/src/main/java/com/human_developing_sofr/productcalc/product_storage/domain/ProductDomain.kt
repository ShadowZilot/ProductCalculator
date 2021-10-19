package com.human_developing_sofr.productcalc.product_storage.domain

sealed interface ProductDomain {
    fun <T> map(mapper: ProductDomain.Mapper<T>): T

    interface Mapper<T> {
        fun map(): T
    }
}