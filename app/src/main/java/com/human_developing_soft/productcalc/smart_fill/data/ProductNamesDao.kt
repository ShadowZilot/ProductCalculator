package com.human_developing_soft.productcalc.smart_fill.data

import androidx.room.*

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
@Dao
interface ProductNamesDao {
    @Transaction
    @Query("SELECT * FROM names")
    suspend fun allNames(): List<ProductsAndName>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertName(name: ProductName)
}