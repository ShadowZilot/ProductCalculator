package com.human_developing_soft.productcalc.history.ui

import android.content.Context
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.product_storage.StringContext
import java.text.SimpleDateFormat
import java.util.*

interface DateProvider {
    fun date(): String

    class Base(
        time: Long,
        context: Context
    ) : DateProvider {
        private val mString = StringContext.Base(context)
        private val mCalender : Calendar = GregorianCalendar.getInstance()

        init {
            mCalender.time = Date(time)
        }

        override fun date(): String {
            val format = SimpleDateFormat(mString.string(R.string.date_format))
            return format.format(mCalender.time)
        }
    }

}