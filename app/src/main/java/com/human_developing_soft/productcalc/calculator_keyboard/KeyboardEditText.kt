package com.human_developing_soft.productcalc.calculator_keyboard

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.human_developing_soft.productcalc.databinding.CalculatorKeyboardGridBinding


class KeyboardEditText(
    context: Context,
    attributeSet: AttributeSet
) : KeyboardlessEditText2(
    context,
    attributeSet
), KeyActionListener {
    private var mKeyBoardParent: ViewGroup? = null
    private var mActivity: FragmentActivity? = null
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
        keyboardParent: ViewGroup,
        activity: FragmentActivity,
    ) {
        mKeyBoardParent = keyboardParent
        mActivity = activity
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
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        mKeyBoard?.isVisible(focused)
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        Log.d("Keyboard", "Selected index = $selStart")
        mKeyBoard?.onIndexChanged(selStart)
    }

    override fun onKeyPressed(keyAction: CalculationAction, index: Int) {
        val oldLength = text!!.length
        setText(
            keyAction.implementAction(text.toString())
        )
        setSelection(
            IndexChanging.Base(
                keyAction,
                index,
                oldLength,
                text!!.length
            ).index()
        )
    }
}