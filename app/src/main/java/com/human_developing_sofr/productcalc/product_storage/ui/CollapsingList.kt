package com.human_developing_sofr.productcalc.product_storage.ui

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import com.human_developing_sofr.productcalc.R
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
            mBinding.root.alpha = 0f
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
            mProductCollapse = ProductCollapsing(
                productView,
                ProductAdapter(mProductsEventListener), R.string.collapse_title_product
            )
            mExpenditureCollapse = ExpenditureCollapsing(
                expenditureView,
                ExpenditureAdapter(mExpenditureEventListener), R.string.collapse_title_expenditure
            )
        }

        override fun fetchList(
            products: List<ProductUi>,
            expenditures: List<ExpenditureUi>
        ) {
            val animator = ObjectAnimator.ofFloat(mBinding.root,
                "alpha", 0f, 1f)
            animator.duration = 150
            animator.start()
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