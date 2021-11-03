package com.human_developing_soft.productcalc.history.domain

interface TemporaryMonthStore {
    fun result(): List<MonthDomain>

    fun add(days: List<Long>)

    class Base : TemporaryMonthStore {
        private val mResult = mutableListOf<MonthDomain>()

        override fun result() = mResult.toList()

        override fun add(days: List<Long>) {
            mResult.add(
                MonthDomain.Base(
                    days
                )
            )
        }
    }
}