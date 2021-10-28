package com.human_developing_sofr.productcalc.product_storage.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.human_developing_sofr.productcalc.databinding.CollapsingItemBinding
import com.human_developing_sofr.productcalc.databinding.CollapsingListBinding

interface CollapsingList {

    fun hide()

    fun fetchList(
        products: List<ProductUi>,
        expenditures: List<ExpenditureUi>
    )

    class Base(
        private val mBinding: CollapsingListBinding,
        private val mProductsEventListener: OnProductClickListener,
        private val mExpenditureEventListener: OnExpenditureClickListener
    ) : CollapsingList {
        private lateinit var mProductCollapse: BaseCollapsingView<ProductUi>
        private lateinit var mExpenditureCollapse: BaseCollapsingView<ExpenditureUi>

        override fun hide() {
            mBinding.root.visibility = View.GONE
            mBinding.collapsingLayout.removeAllViews()
            val productView = CollapsingItemBinding.inflate(
                LayoutInflater.from(
                    mBinding.collapsingLayout.context
                )
            )
            val expenditureView = CollapsingItemBinding.inflate(
                LayoutInflater.from(
                    mBinding.collapsingLayout.context
                )
            )
            mBinding.collapsingLayout.apply {
                this.addView(productView.root)
                this.addView(expenditureView.root)
            }
            mProductCollapse = BaseCollapsingView.ProductCollapsing(
                productView, ProductAdapter(mProductsEventListener)
            )
            mExpenditureCollapse = BaseCollapsingView.ExpenditureCollapsing(
                expenditureView, ExpenditureAdapter(mExpenditureEventListener)
            )
        }

        override fun fetchList(
            products: List<ProductUi>,
            expenditures: List<ExpenditureUi>
        ) {
            // TODO add animation for list
            mBinding.root.visibility = View.VISIBLE
            var productSumma = 0
            for (product in products) {
                productSumma += product.map(ProductPrice())
            }
            var expenditureSumma = 0
            for (expenditure in expenditures) {
                expenditureSumma += expenditure.map(ExpenditurePrice())
            }
            mProductCollapse.fetchData(products, productSumma)
            mExpenditureCollapse.fetchData(expenditures, expenditureSumma)
        }
    }
}