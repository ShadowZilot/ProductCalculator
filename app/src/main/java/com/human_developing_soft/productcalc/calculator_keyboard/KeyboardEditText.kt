package com.human_developing_soft.productcalc.calculator_keyboard

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.google.android.material.textfield.TextInputEditText
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding

class KeyboardEditText(context: Context,
                       attributeSet: AttributeSet) : TextInputEditText(context,
    attributeSet), KeyActionListener {
    private var mKeyBoardParent : ViewGroup? = null
    private var mActivity : FragmentActivity? = null
    private var mKeyBoard: CustomKeyboard? = null

    /**
     * This method necessary to invoke during creating views.
     * It's need for normal view work. If you forget to invoke this method,
     * you will get NPE error.
     *
     * @param keyboardParent it's keyboard parent view
     * @param activity it needs for creating keyboard
     *
     */
    fun setupKeyboardComponents(
        keyboardParent : ViewGroup,
        activity: FragmentActivity,
    ) {
        mKeyBoardParent = keyboardParent
        mActivity = activity
        val keyboardView = CalculatorKeyboardGridBinding.inflate(
            LayoutInflater.from(context),
            keyboardParent,
            false
        )
        mKeyBoard = CustomKeyboard.Base(keyboardView,
            this)
        mKeyBoardParent?.addView(keyboardView.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm: InputMethodManager = mActivity?.applicationContext?.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(
            windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        return if (isFocused) {
            super.onTouchEvent(event)
        } else {
            requestFocus()
            performClick()
        }
    }

    override fun onFocusChanged(focused: Boolean,
                                direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        mKeyBoard?.isVisible(focused)
    }

    override fun onKeyPressed(keyAction: CalculationAction) {
        setText(
            keyAction.implementAction(text.toString())
        )
    }
}