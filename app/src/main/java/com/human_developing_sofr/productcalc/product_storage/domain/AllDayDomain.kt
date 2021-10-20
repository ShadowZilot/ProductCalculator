package com.human_developing_sofr.productcalc.product_storage.domain

interface AllDayDomain {

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mDay: DayDomain,
        private val mProducts: List<DomainProduct>
    ) : AllDayDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mDay,
                mProducts
            )
        }
    }

    class Dummy : AllDayDomain {
        override fun <T> map(mapper: Mapper<T>): T {
            throw DayNotFoundException("Handle with dummy AllDayDomain")
        }
    }

    interface Mapper<T> {
        fun map(
            day: DayDomain,
            products: List<DomainProduct>
        ): T
    }
}