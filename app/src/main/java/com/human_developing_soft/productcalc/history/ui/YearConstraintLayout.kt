package com.human_developing_soft.productcalc.history.ui

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
class YearConstraintLayout(
    context: Context,
    attrs : AttributeSet
) : ConstraintLayout(context, attrs) {
    private var mYear : Int = 0

    fun updateYear(year : Int) {
        mYear = year
    }

    fun year() = mYear
}