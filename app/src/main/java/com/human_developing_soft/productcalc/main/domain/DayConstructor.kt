package com.human_developing_soft.productcalc.main.domain

import com.human_developing_soft.productcalc.product_storage.data.AllDay
import com.human_developing_soft.productcalc.product_storage.data.Day
import com.human_developing_soft.productcalc.product_storage.data.Product

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface DayConstructor {
    fun constructedDay(): AllDay

    class Base(
        private val mDayId: Int,
        private val mTime: Long,
        private val mExpenditureConstructor: ExpenditureConstructor,
        private val mProductConstructor: ProductConstructor
    ) : DayConstructor {

        override fun constructedDay(): AllDay {
            val productSize = (5..12).random()
            val expenditureSize = (2..5).random()
            val productList = mutableListOf<Product>()
            val expenditureList = mutableListOf<Product>()
            for (i in 1..productSize) {
                productList.add(mProductConstructor.constructedProduct())
            }
            for (i in 1..expenditureSize) {
                expenditureList.add(mExpenditureConstructor.constructedExpenditure())
            }
            return AllDay(
                Day(
                    mDayId,
                    (15000..120000).random(),
                    mTime
                ),
                productList + expenditureList
            )
        }
    }
}