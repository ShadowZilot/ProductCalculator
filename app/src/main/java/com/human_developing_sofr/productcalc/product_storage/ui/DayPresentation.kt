package com.human_developing_sofr.productcalc.product_storage.ui

import androidx.annotation.StringRes

interface DayPresentation : AllDayUi.Mapper<Unit> {

    fun startLoading()

    fun raiseError(@StringRes message: Int)

    class Base(
        private val mMoneyInfo: MoneyInfoView,
        private val mProductsList: AllProductsView
    ) : DayPresentation {

        override fun startLoading() {
            mProductsList.beginLoading()
        }

        override fun raiseError(message: Int) {
            mProductsList.fetchData(
                emptyList(),
                message
            )
        }

        override fun map(day: DayUi, products: List<ProductUi>) {
            day.map(mMoneyInfo)
            mProductsList.fetchData(
                products
            )
        }
    }
}