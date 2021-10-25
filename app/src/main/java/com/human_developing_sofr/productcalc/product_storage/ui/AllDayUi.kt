package com.human_developing_sofr.productcalc.product_storage.ui

interface AllDayUi {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val mDay: DayUi,
        private val mProducts: List<ProductUi>,
        private val mExpenditures: List<ExpenditureUi>
    ) : AllDayUi {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(
                mDay,
                mProducts,
                mExpenditures
            )
        }
    }

    interface Mapper<T> {
        fun map(
            day: DayUi,
            products: List<ProductUi>,
            expenditures: List<ExpenditureUi>
        ): T
    }
}