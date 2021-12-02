package com.human_developing_soft.productcalc.add_editing.products.ui

import android.content.Context
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.product_storage.ui.ProductUi

interface ProductUiFactory {
    fun create(context: Context, isDeleting: Boolean): ProductUi

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mWeight: String,
        private val mPrice: String,
        private val mPlaceRow: String,
        private val mSummaryPrice: String,
        private val mNote: String
    ): ProductUiFactory {
        override fun create(context: Context, isDeleting: Boolean): ProductUi {
            return if (mName != "" &&
                mWeight != "" &&
                mPrice != "") {
                 ProductUi.Base(
                    mId,
                    mName,
                    mWeight.toFloat(),
                    mPrice.toFloat(),
                    if (mSummaryPrice.isEmpty())
                       mPrice.toFloat() * mWeight.toFloat() else mSummaryPrice.toFloat(),
                    if (mPlaceRow == "") "" else mPlaceRow,
                    mNote
                )
            } else {
                if (isDeleting) {
                    ProductUi.Base(mId, "",
                        0f, 0f,
                        0f, "", "")
                } else {
                    ProductUi.Fail(R.string.fail_empty_fields,
                        context)
                }
            }
        }
    }
}