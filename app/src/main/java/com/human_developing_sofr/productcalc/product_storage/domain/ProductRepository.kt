package com.human_developing_sofr.productcalc.product_storage.domain

interface ProductRepository {
    suspend fun allProducts(data: Long): List<DomainProduct> {return listOf()}

    suspend fun insertProduct(product: DomainProduct) {}

    suspend fun productById(id: Int): DomainProduct {return DomainProduct.Dummy()}

    suspend fun updateProduct(product: DomainProduct) {}

    suspend fun deleteProduct(product: DomainProduct) {}
}