package com.human_developing_soft.productcalc.product_storage.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.human_developing_soft.productcalc.smart_fill.data.ProductName

@Database(entities = [Product::class,
    Day::class,
    ProductName::class],
    version = 3)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}