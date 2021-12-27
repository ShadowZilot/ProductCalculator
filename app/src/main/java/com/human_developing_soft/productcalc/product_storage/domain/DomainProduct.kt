package com.human_developing_soft.productcalc.product_storage.domain

interface DomainProduct {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int?,
        private val mProductNameId: Int?,
        private val mName: String,
        private val mWeight: Float,
        private val mPriceForWeight: Float,
        private val mPlaceRow: String,
        private val mNote: String
    ) : DomainProduct {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mProductNameId,
                mName,
                mWeight,
                mPriceForWeight,
                mPlaceRow,
                mNote
            )
        }
    }

    class Dummy : DomainProduct {
        override fun <T> map(mapper: Mapper<T>): T {
            throw Exception("Handle with empty DomainProduct")
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int?,
            productNameId: Int?,
            name: String,
            weight: Float,
            priceForWeight: Float,
            placeRow: String,
            note: String
        ): T
    }
}