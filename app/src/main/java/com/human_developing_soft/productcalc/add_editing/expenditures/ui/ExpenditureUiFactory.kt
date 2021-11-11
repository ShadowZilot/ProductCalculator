package com.human_developing_soft.productcalc.add_editing.expenditures.ui

import android.content.Context
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.product_storage.ui.ExpenditureUi

interface ExpenditureUiFactory {
    fun create(context: Context, isDeleting: Boolean): ExpenditureUi

    class Base(
        private val mId: Int?,
        private val mName: String,
        private val mCost: String,
        private val mNote: String
    ) : ExpenditureUiFactory {
        override fun create(context: Context, isDeleting: Boolean): ExpenditureUi {
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
                if (isDeleting) {
                    ExpenditureUi.Base(mId, "", 0f, "")
                } else {
                    ExpenditureUi.Fail(
                        context,
                        R.string.fail_empty_fields
                    )
                }
            }
        }
    }
}