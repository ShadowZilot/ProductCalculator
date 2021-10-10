package com.human_developing_sofr.productcalc.history.ui

import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.product_storage.ui.ProductsListEmptyView

interface HistoryList {

    fun beginLoading()

    fun fetchData(data: List<MonthUi>)

    class Base(
        private val mList: RecyclerView,
        private val mProgress: ProductsListEmptyView,
        private val mObserver: OnYearScrolledListener

    ) : HistoryList {
        override fun beginLoading() {
            mList.alpha = 0f
            mProgress.beginLoading()
        }

        override fun fetchData(data: List<MonthUi>) {
            mProgress.stopLoading(data.isNotEmpty())

        }
    }
}