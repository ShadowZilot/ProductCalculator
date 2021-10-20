package com.human_developing_sofr.productcalc.product_storage.data

interface ExpenditureData {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int,
        private val mName: String,
        private val mPrice: Float,
        private val mNote: String,
        private val mTime: Long
    ) : ExpenditureData {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mName,
                mPrice,
                mNote,
                mTime
            )
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int,
            name: String,
            price: Float,
            note: String,
            time: Long
        ): T
    }
}