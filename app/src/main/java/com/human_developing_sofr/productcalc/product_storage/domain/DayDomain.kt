package com.human_developing_sofr.productcalc.product_storage.domain

interface DayDomain {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mId: Int,
        private val mMoney: Int
    ) : DayDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mId,
                mMoney
            )
        }
    }

    interface Mapper<T> {
        fun map(
            id: Int,
            money: Int
        ): T
    }
}