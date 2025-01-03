package com.human_developing_soft.productcalc.calculator_keyboard.ui

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.human_developing_soft.productcalc.calculator_keyboard.CalculationAction
import com.human_developing_soft.productcalc.calculator_keyboard.CustomKeyboard
import com.human_developing_soft.productcalc.calculator_keyboard.HiddenKeyboard
import com.human_developing_soft.productcalc.calculator_keyboard.IndexChanging
import com.human_developing_soft.productcalc.calculator_keyboard.KeyActionListener
import com.human_developing_soft.productcalc.calculator_keyboard.KeyboardHiding
import com.human_developing_soft.productcalc.calculator_keyboard.OnKeyboardShownListener
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding


class KeyboardEditText(
    context: Context,
    attributeSet: AttributeSet
) : KeyboardlessEditText2(
    context,
    attributeSet
), KeyActionListener, HiddenKeyboard {
    private var mKeyBoardParent: ViewGroup? = null
    private var mActivity: FragmentActivity? = null
    private var mKeyBoard: CustomKeyboard? = null
    private var mListener: OnKeyboardShownListener? = null

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
        keyboardParent: ViewGroup,
        activity: FragmentActivity,
        keyboardListener: OnKeyboardShownListener?
    ) {
        mListener = keyboardListener
        mKeyBoardParent = keyboardParent
        mActivity = activity
        (mActivity as KeyboardHiding).registerKeyboard(this)
        val keyboardView = CalculatorKeyboardGridBinding.inflate(
            LayoutInflater.from(context),
            keyboardParent,
            false
        )
        mKeyBoard = CustomKeyboard.Base(
            keyboardView,
            this
        )
        mKeyBoardParent?.addView(keyboardView.root)
    }

    fun isTextValid(): Boolean {
        return try {
            text.toString().toFloat()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun numberedText(): Float {
        return text.toString().toFloat()
    }

    override fun onFocusChanged(
        focused: Boolean,
        direction: Int, previouslyFocusedRect: Rect?
    ) {
        mKeyBoard?.updateVisibility(focused)
        if (focused) {
            mListener?.onShow(this)
        } else {
            mListener?.onHide(this)
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        Log.d("Keyboard", "Selected index = $selStart")
        mKeyBoard?.onIndexChanged(selStart)
    }

    override fun onKeyPressed(keyAction: CalculationAction, index: Int) {
        val oldLength = text?.length ?: 0
        setText(keyAction.implementAction(text.toString()))
        val newLength = text?.length ?: 0
        if (oldLength != newLength) {
            setSelection(
                IndexChanging.Base(
                    keyAction,
                    index,
                    oldLength,
                    text?.length ?: 0
                ).index()
            )
        }
    }

    override fun hide() {
        clearFocus()
        mListener?.onHide(this)
        mKeyBoard?.updateVisibility(false)
    }

    override fun isHidden(): Boolean {
        return mKeyBoard?.isVisible() ?: false
    }
}