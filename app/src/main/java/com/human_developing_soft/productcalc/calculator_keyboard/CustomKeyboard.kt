package com.human_developing_soft.productcalc.calculator_keyboard

import android.view.View
import android.widget.Button
import androidx.core.view.children
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding

interface CustomKeyboard {

    fun isVisible(visibility: Boolean)

    class Base(
        private val mKeyboardView: CalculatorKeyboardGridBinding,
        private val mEventListener: KeyActionListener
    ) : CustomKeyboard {

        init {
            mKeyboardView.root.visibility = View.GONE
            for (view in mKeyboardView.keyboardGrid.children) {
                view.setOnClickListener {
                    mEventListener.onKeyPressed(
                        KeyActionRecognition.Base(
                            (it as Button).text.toString()
                        ).keyAction()
                    )
                }
            }
        }

        override fun isVisible(visibility: Boolean) {
            mKeyboardView.root.visibility =
                if (visibility) View.VISIBLE else View.GONE
        }
    }
}