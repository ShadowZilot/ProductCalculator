package com.human_developing_soft.productcalc.add_editing.products.ui

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.human_developing_soft.productcalc.product_storage.ui.toFloatSafety

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface HandlingSummaryField {
    class Base(
        private val mWeight: EditText,
        private val mPriceForWeight: EditText,
        private val mSummary: TextInputLayout
    ) : HandlingSummaryField {
        init {
            mWeight.addTextChangedListener {
                handleChanges()
            }
            mPriceForWeight.addTextChangedListener {
                handleChanges()
            }
        }

        private fun handleChanges() {
            if (
                mWeight.text.isNotEmpty()
                && mPriceForWeight.text.isNotEmpty()
            ) {
                mSummary.visibility = View.VISIBLE
            } else {
                mSummary.visibility = View.GONE
            }
            try {
                mSummary.keyboardLessInput().setText(
                    (mWeight.text.toString().toFloatSafety()
                            * mPriceForWeight.text.toString().toFloatSafety()
                            ).toString()
                )
            } catch (e: NumberFormatException) {
                // do nothing, just wait user's input
            }
        }
    }
}

fun TextInputLayout.keyboardLessInput() : EditText {
    return try {
        ((this.getChildAt(0) as ViewGroup
                ).getChildAt(1)) as EditText
    } catch (e : ClassCastException) {
        ((this.getChildAt(0) as ViewGroup
                ).getChildAt(0)) as EditText
    }
}