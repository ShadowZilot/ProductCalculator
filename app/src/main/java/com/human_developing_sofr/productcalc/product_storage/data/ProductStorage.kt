package com.human_developing_sofr.productcalc.product_storage.data

interface ProductStorage {
    suspend fun allProducts(): List<Product>

    suspend fun insertProduct(product: Product)
}