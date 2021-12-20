package com.human_developing_soft.productcalc.main.domain

import com.human_developing_soft.productcalc.product_storage.data.Product

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface ProductConstructor {
    fun constructedProduct(): Product

    class Base(
        private val mDayInt: Int,
        private val mPossibleList: List<String>
    ) : ProductConstructor {
        override fun constructedProduct(): Product {
            return Product(
                null,
                mDayInt,
                mPossibleList.random(),
                (45..350).random().toFloat(),
                (15..150).random().toFloat(),
                "",
                ""
            )
        }
    }
}