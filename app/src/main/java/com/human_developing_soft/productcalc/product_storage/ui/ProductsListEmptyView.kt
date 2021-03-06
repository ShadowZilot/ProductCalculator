package com.human_developing_soft.productcalc.product_storage.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.StringRes
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.EmptyListLayoutBinding

interface ProductsListEmptyView  {

    fun beginLoading()

    fun stopLoading(
        isSuccess: Boolean,
        @StringRes message: Int = R.string.empty_products_message
    )

    class Base(
        private val mProgress: ProgressBar,
        private val mMessage: TextView
    ) : ProductsListEmptyView {
        constructor(binding: EmptyListLayoutBinding): this(
            binding.productsProgressLoad, binding.emptyProgressText
        )

        override fun beginLoading() {
            mProgress.visibility = View.VISIBLE
            mMessage.visibility = View.GONE
        }

        override fun stopLoading(isSuccess: Boolean,
                        @StringRes message: Int) {
            mProgress.visibility = View.GONE
            mMessage.visibility = if (isSuccess) View.GONE else View.VISIBLE
            mMessage.setText(message)
        }
    }

}