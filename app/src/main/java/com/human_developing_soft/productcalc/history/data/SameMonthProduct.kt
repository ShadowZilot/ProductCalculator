package com.human_developing_soft.productcalc.history.data

import com.human_developing_soft.productcalc.product_storage.data.AllDay
import com.human_developing_soft.productcalc.product_storage.data.Day
import com.human_developing_soft.productcalc.product_storage.data.Product
import java.util.*

class SameMonthProduct(
    private val mOtherDay: AllDay
) : AllDay.Mapper<Boolean> {
    override fun map(day: Day, products: List<Product>): Boolean {
        val selfCalendar = GregorianCalendar()
        selfCalendar.timeInMillis = mOtherDay.getDay().getTime()
        val otherDay = GregorianCalendar()
        otherDay.timeInMillis = day.getTime()
        return (otherDay.get(Calendar.MONTH) == selfCalendar.get(Calendar.MONTH)
                && otherDay.get(Calendar.YEAR) == selfCalendar.get(Calendar.YEAR))
    }
}