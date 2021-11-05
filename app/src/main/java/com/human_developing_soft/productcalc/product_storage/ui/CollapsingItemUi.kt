package com.human_developing_soft.productcalc.product_storage.ui

interface CollapsingItemUi {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mType: Int,
        private val mIsCollapsed: Boolean,
        private val mSumma: Int
    ) : CollapsingItemUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(mType, mIsCollapsed, mSumma)
        }
    }

    interface Mapper<T> {
        fun map(
            type: Int,
            isCollapsed: Boolean,
            summa: Int
        ): T
    }
}