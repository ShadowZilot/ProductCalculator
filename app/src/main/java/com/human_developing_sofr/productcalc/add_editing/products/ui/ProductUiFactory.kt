package com.human_developing_sofr.productcalc.add_editing.products.ui

import android.content.Context
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.product_storage.ui.ProductUi

interface ProductUiFactory {
    fun create(context: Context): ProductUi

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mWeight: String,
        private val mPrice: String,
        private val mPlaceRow: String,
        private val mNote: String
    ): ProductUiFactory {
        override fun create(context: Context): ProductUi {
            return if (mName != "" &&
                mWeight != "" &&
                mPrice != "") {
                 ProductUi.Base(
                    mId,
                    mName,
                    mWeight.toFloat(),
                    mPrice.toFloat(),
                    0f,
                    if (mPlaceRow == "") "" else mPlaceRow,
                    mNote
                )
            } else {
                ProductUi.Fail(R.string.fail_empty_fields,
                    context)
            }
        }
    }
}