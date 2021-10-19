package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import com.human_developing_sofr.productcalc.product_storage.data.DataToDomainProduct
import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage

class ProductsUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage
        .Instance.database(context)

    override suspend fun allProducts(data: Long): List<DomainProduct> {
        val suitableProducts = mutableListOf<Product>()
        for (product in mDatabase.allProducts()) {
            // TODO Implements this method with new database
            if (false) {
                suitableProducts.add(product)
            }
        }
        return suitableProducts.reversed().map {
            it.map(DataToDomainProduct())
        }
    }
}