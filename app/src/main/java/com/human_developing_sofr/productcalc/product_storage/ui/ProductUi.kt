package com.human_developing_sofr.productcalc.product_storage.ui

interface ProductUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int,
        private val mName: String,
        private val mWeight: Float,
        private val mPriceForWeight: Float,
        private val mPlaceRow: Int,
        private val mNote: String
    ) : ProductUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mName,
                mWeight,
                mPriceForWeight,
                mPlaceRow,
                mNote
            )
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int,
            name: String,
            weight: Float,
            priceForWeight: Float,
            placeRow: Int,
            note: String
        ): T
    }
}