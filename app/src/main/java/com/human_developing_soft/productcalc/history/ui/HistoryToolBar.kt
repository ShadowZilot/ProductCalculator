package com.human_developing_soft.productcalc.history.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.MaterialToolbar

class HistoryToolBar(
    context: Context,
    set: AttributeSet?
) : MaterialToolbar(context, set), OnYearScrolledListener {
    override fun onYearUpdate(year: Int) {
        subtitle = year.toString()
    }
}