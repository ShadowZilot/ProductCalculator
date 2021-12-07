package com.human_developing_soft.productcalc.history.ui

import android.content.Context
import com.human_developing_soft.productcalc.R

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface DayOfWeekProvider {
    fun dayOfWeek(order: Int): String

    class Base(
        private val mContext: Context
    ) : DayOfWeekProvider {
        override fun dayOfWeek(order: Int): String {
            val resources = mContext.resources
            return resources.getStringArray(R.array.days_of_week)[order]
        }
    }
}