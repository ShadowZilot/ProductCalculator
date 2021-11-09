package com.human_developing_soft.productcalc.history.domain

import com.human_developing_soft.productcalc.history.data.SameMonthProduct
import com.human_developing_soft.productcalc.product_storage.data.AllDay
import java.lang.IndexOutOfBoundsException

interface MonthSeparatingList {
    fun separate(): List<MonthDomain>

    class Base(
        private val mInput: List<AllDay>
    ) : MonthSeparatingList {
        override fun separate(): List<MonthDomain> {
            val result = TemporaryMonthStore.Base()
            val tmp = mutableListOf<Long>()
            for (i in mInput.indices) {
                try {
                    tmp.add(mInput[i].getDay().getTime())
                    if (!mInput[i].map(SameMonthProduct(mInput[i+1]))) {
                        result.add(tmp.toList())
                        tmp.clear()
                    }
                } catch (e : IndexOutOfBoundsException) {
                    result.add(tmp)
                }
            }
            return result.result()
        }
    }
}