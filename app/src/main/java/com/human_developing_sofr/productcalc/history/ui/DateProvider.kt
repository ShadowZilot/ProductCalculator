package com.human_developing_sofr.productcalc.history.ui

import android.content.Context
import com.human_developing_sofr.productcalc.R
import com.human_developing_sofr.productcalc.product_storage.StringContext
import java.util.*

interface DateProvider {
    fun date(): String

    class Base(
        time: Long,
        context: Context
    ) : DateProvider {
        private val mString = StringContext.Base(context)
        private val mMonthProvider = MonthProvider.Base(context)
        private val mCalender : Calendar = GregorianCalendar.getInstance()

        init {
            mCalender.time = Date(time)
        }

        override fun date(): String {
            return mString.string(
                R.string.date_format,
                mCalender.get(Calendar.YEAR),
                mMonthProvider.monthByOrder(
                    mCalender.get(Calendar.MONTH)
                ),
                mCalender.get(Calendar.DAY_OF_MONTH)
            )
        }
    }

}