package com.human_developing_soft.productcalc.product_storage.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class HiddenFAB(
    context: Context,
    attributeSet: AttributeSet
) : FloatingActionButton(context, attributeSet), OnDirectionChanged {

    override fun onChanges(direction: Int) {
        if (direction == OnDirectionChanged.TO_BOTTOM) {
            hide()
        } else {
            show()
        }
    }
}