package com.human_developing_sofr.productcalc.product_storage.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    suspend fun allProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(newProduct: Product)
}