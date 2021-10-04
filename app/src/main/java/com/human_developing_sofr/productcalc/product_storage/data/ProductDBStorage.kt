package com.human_developing_sofr.productcalc.product_storage.data

import android.content.Context
import androidx.room.Room

class ProductDBStorage private constructor(
    context: Context
): ProductStorage {
    private val mDatabase: ProductDatabase = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        "Products.db"
    ).build()

    override suspend fun allProducts(): List<Product> {
        return mDatabase
            .productDao()
            .allProducts()
    }

    override suspend fun insertProduct(product: Product) {
        mDatabase
            .productDao()
            .insertProduct(product)
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