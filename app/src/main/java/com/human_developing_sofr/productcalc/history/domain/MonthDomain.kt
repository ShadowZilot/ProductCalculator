package com.human_developing_sofr.productcalc.history.domain

import java.util.*

interface MonthDomain {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mTime: Date,
        private val mDays: List<Date>
    ): MonthDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mTime,
                mDays
            )
        }
    }

    interface Mapper<T> {
        fun map(
            time: Date,
            days: List<Date>
        ): T
    }
}