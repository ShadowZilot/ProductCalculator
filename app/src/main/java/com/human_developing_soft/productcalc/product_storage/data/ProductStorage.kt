package com.human_developing_soft.productcalc.product_storage.data

interface ProductStorage {
    suspend fun allDays(): List<AllDay>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun createDay(day: Day)

    suspend fun dayById(id: Int): Day

    suspend fun updateDay(day: Day)

    suspend fun deleteProduct(product: Product)

    suspend fun productById(id: Int): Product

    suspend fun deleteDay(id: Int)
}