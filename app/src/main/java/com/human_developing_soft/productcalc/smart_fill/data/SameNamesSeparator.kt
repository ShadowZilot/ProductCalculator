package com.human_developing_soft.productcalc.smart_fill.data

import com.human_developing_soft.productcalc.product_storage.data.NotExpenditureException
import com.human_developing_soft.productcalc.product_storage.data.Product
import com.human_developing_soft.productcalc.product_storage.data.ProductToExpenditure
import com.human_developing_soft.productcalc.smart_fill.same_names.IsSimilarProduct

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface SameNamesSeparator {
    suspend fun separate()

    class ExpenditureSeparating(
        private val mDao: SameNamesUseCase
    ) : SameNamesSeparator {
        override suspend fun separate() {

        }
    }

    class ProductSeparating(
        private val mDao: SameNamesUseCase
    ) : SameNamesSeparator {
        override suspend fun separate() {
            val holeProducts = mutableListOf<Product>()
            for (product in mDao.products()) {
                try {
                    product.map(ProductToExpenditure())
                } catch (e: NotExpenditureException) {
                    holeProducts.add(product)
                }
            }
            val tmpList = mutableListOf<Product>()
            val index = 0
            tmpList.add(holeProducts[index])
            while (holeProducts.size > 0) {
                for (i in 0 until holeProducts.size) {
                    if (holeProducts[i].map(
                            IsSimilarProduct(
                                holeProducts[index]
                            )
                        )
                    ) {
                        tmpList.add(holeProducts[i])
                    }
                }

                holeProducts.removeAll(tmpList)
            }
        }
    }
}