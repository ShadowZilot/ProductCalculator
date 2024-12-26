package com.human_developing_soft.productcalc.calculator_keyboard.ui

import android.R.attr.spacing
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.GridLayout
import androidx.core.view.children
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.core.view.marginTop


class CalculatorKeyboardLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridLayout(context, attributeSet, defStyleAttr) {

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val width = MeasureSpec.getSize(widthSpec)
        var allVerticalMargins = 0
        val desiredChildSize = (width - paddingEnd - paddingStart) / columnCount
        children.forEach { child ->
            val childWidthSpec = MeasureSpec.makeMeasureSpec(
                desiredChildSize - child.marginStart - child.marginEnd,
                MeasureSpec.EXACTLY
            )
            val childHeightSpec = MeasureSpec.makeMeasureSpec(
                desiredChildSize - child.marginTop - child.marginBottom,
                MeasureSpec.EXACTLY
            )
            allVerticalMargins += child.marginTop + child.marginBottom
            child.measure(childWidthSpec, childHeightSpec)
        }
        setMeasuredDimension(
            width,
            desiredChildSize * rowCount + paddingTop + paddingBottom
        )
    }
}