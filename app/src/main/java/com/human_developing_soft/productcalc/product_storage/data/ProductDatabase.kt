package com.human_developing_soft.productcalc.product_storage.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class, Day::class], version = 2)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}