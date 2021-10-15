package com.human_developing_sofr.productcalc.product_storage.domain

import android.content.Context
import com.human_developing_sofr.productcalc.product_storage.data.DataToDomainProduct
import com.human_developing_sofr.productcalc.product_storage.data.Product
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.product_storage.data.SameProductDay

class ProductsUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage
        .Instance.database(context)

    override suspend fun allProducts(data: Long): List<DomainProduct> {
        val suitableProducts = mutableListOf<Product>()
        for (product in mDatabase.allProducts()) {
            if (product.map(SameProductDay(data))) {
                suitableProducts.add(product)
            }
        }
        return suitableProducts.reversed().map {
            it.map(DataToDomainProduct())
        }
    }
}