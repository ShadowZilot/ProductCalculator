package com.human_developing_soft.productcalc.history.ui

import android.animation.ObjectAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.product_storage.ui.ProductsListEmptyView

interface HistoryList {

    fun beginLoading()

    fun fetchData(data: List<MonthUi>)

    class Base(
        private val mList: RecyclerView,
        private val mProgress: ProductsListEmptyView,
        private val mObserver: OnYearScrolledListener,
        clickerObserver: OnDayItemClicked
    ) : HistoryList {

        init {
            mList.adapter = HistoryAdapter(clickerObserver)
            mList.addItemDecoration(DividerItemDecoration(mList.context,
                RecyclerView.VERTICAL))
            mList.layoutManager = LinearLayoutManager(mList.context,
                RecyclerView.VERTICAL,
                false)
            mList.addOnScrollListener(
                ListScrollingListener(
                    mObserver
                )
            )
        }

        override fun beginLoading() {
            mList.alpha = 0f
            mProgress.beginLoading()
        }

        override fun fetchData(data: List<MonthUi>) {
            mProgress.stopLoading(data.isNotEmpty())
            if (data.isNotEmpty()) {
                (mList.adapter as HistoryAdapter).fetchData(data)
                val animator = ObjectAnimator.ofFloat(
                    mList, "alpha", 0f, 1f
                )
                animator.duration = 100
                animator.start()
            }
            mObserver.onYearUpdate(
                data[0].map(YearByMonthUi())
            )
        }
    }
}