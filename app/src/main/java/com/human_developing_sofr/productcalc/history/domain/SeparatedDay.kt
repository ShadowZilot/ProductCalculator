package com.human_developing_sofr.productcalc.history.domain

interface SeparatedDay {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mCommonDayTime: Long
    ): SeparatedDay {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(mCommonDayTime)
        }
    }

    interface Mapper<T> {
        fun map(commonTime: Long): T
    }
}