package com.human_developing_sofr.productcalc.product_storage.domain

import java.lang.Exception

interface ExpenditureDomain {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mPrice: Float,
        private val mNote: String
    ) : ExpenditureDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mName,
                mPrice,
                mNote
            )
        }
    }

    class Dummy : ExpenditureDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            throw Exception(
                "Handle with empty ${ExpenditureDomain::class.java.simpleName}"
            )
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int?,
            name: String,
            price: Float,
            note: String
        ): T
    }
}