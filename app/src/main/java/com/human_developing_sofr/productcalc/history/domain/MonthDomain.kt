package com.human_developing_sofr.productcalc.history.domain

interface MonthDomain {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mDays: List<Long>
    ): MonthDomain {
        private val mTime: Long = mDays[0]
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mTime,
                mDays
            )
        }
    }

    interface Mapper<T> {
        fun map(
            time: Long,
            days: List<Long>
        ): T
    }
}