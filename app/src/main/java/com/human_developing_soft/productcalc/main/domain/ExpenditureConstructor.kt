package com.human_developing_soft.productcalc.main.domain

import com.human_developing_soft.productcalc.product_storage.data.ExpenditureToProduct
import com.human_developing_soft.productcalc.product_storage.data.Product
import com.human_developing_soft.productcalc.product_storage.domain.ExpenditureDomain

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface ExpenditureConstructor {
    fun constructedExpenditure(): Product

    class Base(
        private val mDayId: Int,
        private val mPossibleList: List<String>
    ) : ExpenditureConstructor {
        override fun constructedExpenditure(): Product {
            val expenditure = ExpenditureDomain.Base(
                null,
                mPossibleList.random(),
                (50..500).random().toFloat(),
                ""
            )
            return expenditure.map(ExpenditureToProduct(mDayId))
        }
    }
}