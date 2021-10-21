package com.human_developing_sofr.productcalc.product_storage.ui

import android.animation.ObjectAnimator
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_sofr.productcalc.R

interface AllProductsView {
    fun beginLoading()

    fun fetchData(data: List<ProductUi>,
                  @StringRes errorMessage: Int = R.string.empty_products_message)

    class Base(
        private val mList: RecyclerView,
        private val mProcessManager: ProductsListEmptyView,
        listener: OnProductClickListener
    ) : AllProductsView {
        private val mAdapter: BaseAdapter = ProductAdapter(listener)

        init {
            mList.layoutManager = LinearLayoutManager(mList.context,
            LinearLayoutManager.VERTICAL, false)
            mList.adapter = mAdapter as RecyclerView.Adapter<*>
            mList.addItemDecoration(
                DividerItemDecoration(mList.context,
                RecyclerView.VERTICAL)
            )
        }

        override fun beginLoading() {
            mProcessManager.beginLoading()
            mList.alpha = 0f
        }

        override fun fetchData(data: List<ProductUi>,
                               @StringRes errorMessage: Int) {
            if (data.isNotEmpty()) {
                mProcessManager.stopLoading(true)
                mAdapter.fetchData(data)
                val animator = ObjectAnimator.ofFloat(mList,
                    "alpha", 0f, 1f)
                animator.duration = 100
                animator.start()

            } else {
                mProcessManager.stopLoading(false, errorMessage)
            }
        }
    }
}