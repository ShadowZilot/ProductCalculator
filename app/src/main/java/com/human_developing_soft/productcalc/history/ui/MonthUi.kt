package com.human_developing_soft.productcalc.history.ui

interface MonthUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mMonthName: String,
        private val mDays: List<Long>
    ) : MonthUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(mMonthName, mDays)
        }
    }

    interface Mapper<T> {
        fun map(name: String,
            days: List<Long>): T
    }
}