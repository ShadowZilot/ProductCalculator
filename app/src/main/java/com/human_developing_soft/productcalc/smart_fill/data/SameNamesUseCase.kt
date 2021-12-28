package com.human_developing_soft.productcalc.smart_fill.data

import com.human_developing_soft.productcalc.main.domain.ProductsInject
import com.human_developing_soft.productcalc.product_storage.data.Product
import com.human_developing_soft.productcalc.product_storage.data.ProductDao

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface SameNamesUseCase {
    suspend fun products(): List<Product>

    suspend fun updateProducts(products: List<Product>)

    suspend fun insertName(name: ProductName)

    class Base(
        private val mProducts: ProductDao,
        private val mNames: ProductNamesDao
    ) : SameNamesUseCase {
        override suspend fun products(): List<Product> {
            val products = mutableListOf<Product>()
            mProducts.allDays().forEach {
                products += it.map(ProductsInject())
            }
            return products
        }

        override suspend fun updateProducts(products: List<Product>) {
            products.forEach {
                mProducts.updateProduct(it)
            }
        }

        override suspend fun insertName(name: ProductName) {
            mNames.insertName(name)
        }
    }
}