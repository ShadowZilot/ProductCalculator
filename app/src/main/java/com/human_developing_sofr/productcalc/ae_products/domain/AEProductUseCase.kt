package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import com.human_developing_sofr.productcalc.ae_products.data.DomainToDataProduct
import com.human_developing_sofr.productcalc.product_storage.data.ProductDBStorage
import com.human_developing_sofr.productcalc.product_storage.domain.DomainProduct
import com.human_developing_sofr.productcalc.product_storage.domain.ProductRepository

class AEProductUseCase(
    context: Context
) : ProductRepository {
    private val mDatabase = ProductDBStorage
        .Instance.database(context)

    override suspend fun insertProduct(product: DomainProduct) {
        mDatabase.insertProduct(
            product.map(DomainToDataProduct())
        )
    }
}