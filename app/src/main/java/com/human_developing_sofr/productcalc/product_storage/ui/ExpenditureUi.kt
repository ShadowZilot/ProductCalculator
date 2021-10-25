package com.human_developing_sofr.productcalc.product_storage.ui

interface ExpenditureUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int,
        private val mName: String,
        private val mCost: Float,
        private val mNote: String
    ) : ExpenditureUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(mId, mName, mCost, mNote)
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int,
            name: String,
            cost: Float,
            note: String
        ): T
    }
}