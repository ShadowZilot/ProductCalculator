package com.human_developing_soft.productcalc.calculator_keyboard

import android.view.View
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding

interface CustomKeyboard {

    fun isVisible(visibility: Boolean)

    class Base(
        private val mKeyboardView: CalculatorKeyboardGridBinding
    ) : CustomKeyboard {

        override fun isVisible(visibility: Boolean) {
            mKeyboardView.root.visibility =
                if (visibility) View.VISIBLE else View.GONE
        }

    }
}