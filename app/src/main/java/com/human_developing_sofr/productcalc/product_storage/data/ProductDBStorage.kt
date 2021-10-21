package com.human_developing_sofr.productcalc.product_storage.data

import android.content.Context
import androidx.room.Room

class ProductDBStorage private constructor(
    context: Context
): ProductStorage {
    private val mDatabase: ProductDatabase = Room.databaseBuilder(
        context.applicationContext,
        ProductDatabase::class.java,
        "Products.db"
    ).build()

    override suspend fun allDays(): List<AllDay> {
        return mDatabase
            .productDao()
            .allDays()
    }

    override suspend fun insertProduct(product: Product) {
        mDatabase
            .productDao()
            .insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        mDatabase.productDao().updateProduct(product)
    }

    override suspend fun createDay(day: Day) {
        mDatabase.productDao().createDay(day)
    }

    override suspend fun deleteProduct(product: Product) {
        mDatabase.productDao().deleteProduct(product)
    }

    override suspend fun productById(id: Int): Product {
        return mDatabase.productDao().productById(id)
    }

    object Instance {
        private var mInstance: ProductStorage? = null

        fun database(context: Context): ProductStorage {
            if (mInstance == null) {
                mInstance = ProductDBStorage(context)
            }
            return mInstance!!
        }
    }
}