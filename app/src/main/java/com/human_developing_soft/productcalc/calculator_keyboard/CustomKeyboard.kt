package com.human_developing_soft.productcalc.calculator_keyboard

import android.view.View
import com.human_developing_soft.productcalc.add_editing.KeyActionListener
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding

interface CustomKeyboard {

    fun isVisible(visibility: Boolean)

    class Base(
        private val mKeyboardView: CalculatorKeyboardGridBinding,
        private val mEventListener: KeyActionListener
    ) : CustomKeyboard {

        init {
            mKeyboardView.root.visibility = View.GONE
        }

        override fun isVisible(visibility: Boolean) {
            mKeyboardView.root.visibility =
                if (visibility) View.VISIBLE else View.GONE
        }

    }
}