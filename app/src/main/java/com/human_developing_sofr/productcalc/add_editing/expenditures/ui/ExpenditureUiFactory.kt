package com.human_developing_sofr.productcalc.add_editing.expenditures.ui

import android.content.Context
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.product_storage.ui.ExpenditureUi

interface ExpenditureUiFactory {
    fun create(context: Context): ExpenditureUi

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mCost: String,
        private val mNote: String
    ) : ExpenditureUiFactory {
        override fun create(context: Context): ExpenditureUi {
            return if (
                mName != "" && mCost != ""
            ) {
                ExpenditureUi.Base(
                    mId,
                    mName,
                    mCost.toFloat(),
                    mNote
                )
            } else {
                ExpenditureUi.Fail(
                    context,
                    R.string.fail_empty_fields
                )
            }
        }
    }
}