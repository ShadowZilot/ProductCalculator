package com.human_developing_soft.productcalc.product_storage.ui

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.human_developing_soft.productcalc.databinding.CollapsingListBinding

interface CollapsingList {

    fun hide()

    fun fetchList(
        products: List<ProductUi>,
        expenditures: List<ExpenditureUi>
    )

    class Base(
        private val mBinding: CollapsingListBinding,
        productsEventListener: OnProductClickListener
    ) : CollapsingList {
        private val mAdapter = MainProductAdapter(productsEventListener)

        init {
            mBinding.collapsingLayout.adapter = mAdapter
            mBinding.collapsingLayout.layoutManager = LinearLayoutManager(mBinding.root.context,
                RecyclerView.VERTICAL, false
            )
            mBinding.collapsingLayout.addItemDecoration(DividerItemDecoration(
                mBinding.collapsingLayout.context,
                RecyclerView.VERTICAL
            ))
        }

        override fun hide() {
            mBinding.root.visibility = View.GONE
        }

        override fun fetchList(
            products: List<ProductUi>,
            expenditures: List<ExpenditureUi>
        ) {
            mAdapter.fetchData(products, expenditures)
            mBinding.root.visibility = View.VISIBLE
        }
    }
}