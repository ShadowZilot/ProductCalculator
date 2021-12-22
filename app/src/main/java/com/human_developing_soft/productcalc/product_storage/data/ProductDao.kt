package com.human_developing_soft.productcalc.product_storage.data

import androidx.room.*

@Dao
interface ProductDao {
    @Transaction
    @Query("SELECT * FROM days")
    suspend fun allDays(): List<AllDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(newProduct: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createDay(newDay: Day)

    @Query("SELECT * FROM days WHERE mId = :id")
    suspend fun dayById(id: Int): Day

    @Update
    suspend fun updateDay(day: Day)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM products WHERE mId = :id")
    suspend fun productById(id: Int): Product

    @Query("DELETE FROM days WHERE mId = :id")
    suspend fun deleteDay(id: Int)
}