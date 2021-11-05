package com.human_developing_soft.productcalc.product_storage.ui

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
        }

        override fun hide() {
            mBinding.root.alpha = 0f
        }

        override fun fetchList(
            products: List<ProductUi>,
            expenditures: List<ExpenditureUi>
        ) {
            mAdapter.fetchData(products, expenditures)
            mBinding.root.alpha = 1f
        }
    }
}