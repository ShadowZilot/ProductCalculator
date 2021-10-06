package com.human_developing_sofr.productcalc.product_storage.ui

import android.animation.ObjectAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface AllProductsView {
    fun beginLoading()

    fun fetchData(data: List<ProductUi>)

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

        override fun fetchData(data: List<ProductUi>) {
            mProcessManager.stopLoading(data.isNotEmpty())
            if (data.isNotEmpty()) {
                val animator = ObjectAnimator.ofFloat(mList,
                    "alpha", 0f, 1f)
                animator.duration = 100
                animator.start()
            }
        }
    }
}