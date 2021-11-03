package com.human_developing_sofr.productcalc.product_storage.ui

import androidx.annotation.StringRes
import com.human_developing_sofr.productcalc.R

interface AllProductsView {
    fun beginLoading()

    fun fetchData(
        products: List<ProductUi>,
        expenditures: List<ExpenditureUi>,
        @StringRes errorMessage: Int = R.string.empty_products_message
    )

    class Base(
        private val mList: CollapsingList,
        private val mProcessManager: ProductsListEmptyView,
    ) : AllProductsView {

        override fun beginLoading() {
            mProcessManager.beginLoading()
            mList.hide()
        }

        override fun fetchData(
            products: List<ProductUi>,
            expenditures: List<ExpenditureUi>,
            @StringRes errorMessage: Int
        ) {
            if (products.isNotEmpty() || expenditures.isNotEmpty()) {
                mProcessManager.stopLoading(true)
                mList.fetchList(products, expenditures)
            } else {
                mProcessManager.stopLoading(false, errorMessage)
            }
        }
    }
}