package com.human_developing_sofr.productcalc.product_storage.ui

interface DayUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int,
        private val mMoney: Int,
        private val mProductSumma: Int,
        private val mMoneyRest : Int = mMoney - mProductSumma

    ) : DayUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mMoney,
                mMoneyRest,
                mProductSumma
            )
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int,
            money: Int,
            moneyRest: Int,
            productSumma: Int
        ): T
    }
}