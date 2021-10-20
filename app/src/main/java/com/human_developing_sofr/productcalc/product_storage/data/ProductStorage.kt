package com.human_developing_sofr.productcalc.product_storage.data

interface ProductStorage {
    suspend fun allDays(): List<AllDay>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun productById(id: Int): Product
}