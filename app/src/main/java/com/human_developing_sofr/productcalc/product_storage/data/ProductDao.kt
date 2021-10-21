package com.human_developing_sofr.productcalc.product_storage.data

import androidx.room.*

@Dao
interface ProductDao {
    @Transaction
    @Query("SELECT * FROM days")
    suspend fun allDays(): List<AllDay>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(newProduct: Product)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createDay(newDay: Day)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM products WHERE mId = :id")
    suspend fun productById(id: Int): Product
}