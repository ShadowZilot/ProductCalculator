package com.human_developing_soft.productcalc.history.ui

import android.content.Context
import com.human_developing_soft.productcalc.R

interface MonthProvider {
    fun monthByOrder(order: Int): String

    class Base(
        private val mContext: Context
    ) : MonthProvider {
        override fun monthByOrder(order: Int): String {
            return mContext.resources.getStringArray(
                R.array.months_name)[order]
        }
    }
}