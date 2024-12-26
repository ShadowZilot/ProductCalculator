package com.human_developing_soft.productcalc.calculator_keyboard

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isVisible
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding

interface CustomKeyboard : NotValidFormulaListener, SelectingIndexListener {

    fun updateVisibility(visibility: Boolean)

    fun isVisible(): Boolean

    class Base(
        private val mKeyboardView: CalculatorKeyboardGridBinding,
        private val mEventListener: KeyActionListener
    ) : CustomKeyboard {
        private var mSelectedIndex: Int = 0

        init {
            mKeyboardView.root.visibility = View.GONE
            for (view in mKeyboardView.keyboardGrid.children) {
                view.setOnClickListener {
                    mEventListener.onKeyPressed(
                        KeyActionRecognition.Base(
                            it.tag.toString(),
                            this
                        ).keyAction(mSelectedIndex),
                        mSelectedIndex
                    )
                }
            }
        }

        override fun updateVisibility(visibility: Boolean) {
            mKeyboardView.root.visibility =
                if (visibility) View.VISIBLE else View.GONE
        }

        override fun isVisible(): Boolean {
            return mKeyboardView.root.isVisible
        }

        override fun onFormulaError() {
            Toast.makeText(
                mKeyboardView.root.context,
                R.string.not_valid_formula,
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onEngineError() {
            Toast.makeText(
                mKeyboardView.root.context,
                R.string.engine_error_message,
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onIndexChanged(changedIndex: Int) {
            mSelectedIndex = changedIndex
        }
    }
}