package com.human_developing_sofr.productcalc.ae_products.domain

import android.content.Context
import com.human_developing_sofr.productcalc.ae_products.data.DomainToDataProduct
import com.human_developing_sofr.productcalc.product_storage.data.DataToDomainProduct
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

    override suspend fun updateProduct(product: DomainProduct) {
        super.updateProduct(product)
    }

    override suspend fun deleteProduct(product: DomainProduct) {
        super.deleteProduct(product)
    }

    override suspend fun productById(id: Int): DomainProduct {
        return mDatabase.productById(id).map(
            DataToDomainProduct()
        )
    }
}