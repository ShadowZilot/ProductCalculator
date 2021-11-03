package com.human_developing_soft.productcalc.product_storage.domain

import com.human_developing_soft.productcalc.product_storage.data.AllDay
import com.human_developing_soft.productcalc.product_storage.data.Day
import com.human_developing_soft.productcalc.product_storage.data.Product

class SameDayDate(
    private val mTime : Long
) : AllDay.Mapper<Boolean> {
    override fun map(day: Day, products: List<Product>): Boolean {
        return day.map(SameDayTime(mTime))
    }

    private class SameDayTime(
        private val mTime : Long
    ) : Day.Mapper<Boolean> {
        override fun map(id: Int, money: Int, time: Long): Boolean {
            return mTime.days() == time.days()
        }
    }
}

fun Long.days(): Int {
    return ((this / 1000) / 86400).toInt()
}